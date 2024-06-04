package com.anosman.roombooking.configuration;

import com.anosman.roombooking.services.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    JWTService jwtService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
                        throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if(header == null || !header.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        if(jwtService == null) {
            ServletContext context = request.getServletContext();
            WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(context);
            jwtService = wac.getBean(JWTService.class);
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(header);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
    private UsernamePasswordAuthenticationToken getAuthentication(String header) {
        String jwtToken = header.substring(7);
        try {
            String payload = jwtService.validateToken(jwtToken);
            JsonParser parser = JsonParserFactory.getJsonParser();
            Map<String, Object> payloadMap = parser.parseMap(payload);
            String user = payloadMap.get("user").toString();
            List<String> roles = (List<String>)payloadMap.get("roles");
            List<GrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new)
                                                        .map(GrantedAuthority.class::cast).toList();
            return new UsernamePasswordAuthenticationToken(user, null, authorities);
        } catch (Exception e) {
            // token is not valid
            return null;
        }

    }
}
