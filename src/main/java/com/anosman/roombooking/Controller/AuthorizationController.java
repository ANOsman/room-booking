package com.anosman.roombooking.Controller;

import com.anosman.roombooking.entities.User;
import com.anosman.roombooking.services.JWTService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/basicAuth")
@CrossOrigin(origins = {"http://room.booking.com",
        "http://localhost:4200", "http://room-booking-client-bucket.s3-website.eu-central-1.amazonaws.com"})
public class AuthorizationController {
    @Autowired
    private JWTService jwtService;

    @GetMapping("/validate")
    public Map<String, String> validateUser(HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User)authentication.getPrincipal();
        String name = currentUser.getUsername();
        List<GrantedAuthority> roles = currentUser.getAuthorities();
        List<String> authorities = roles.stream().map(GrantedAuthority::getAuthority).toList();
        String token = jwtService.generateToken(name, authorities);

        Map<String, String> results = new HashMap<>();
        results.put("result", token);
        return results;
    }

//    @GetMapping("/test")
//    public Map<String, String> test() {
//        Map<String, String> test = new HashMap<>();
//        test.put("Test", "Worked");
//        return test;
//    }
}
