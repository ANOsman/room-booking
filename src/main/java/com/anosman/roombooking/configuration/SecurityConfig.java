package com.anosman.roombooking.configuration;

import com.anosman.roombooking.data.UserRepository;
import com.anosman.roombooking.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(UserRepository repository) {
      return username -> {
          User user = repository.findByUsername(username);
          if(user != null) {
              return user;
          }
          throw new UsernameNotFoundException("User '" + username + "' not found");
      };
    }

    @Bean
    SecurityFilterChain loginSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/api/basicAuth/**")
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.OPTIONS,"/api/basicAuth/**").permitAll();
                    auth.requestMatchers("/api/basicAuth/**").hasAnyRole("ADMIN", "USER");
                })
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher(AntPathRequestMatcher.antMatcher("/data-api/**"))
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.OPTIONS,"/data-api/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/data-api/bookings/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/data-api/**").hasAnyRole("ADMIN", "USER");
                    auth.requestMatchers("/data-api/**").hasRole("ADMIN");
                })
                .addFilter(new JWTAuthorizationFilter(authentication -> authentication))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.disable())
                .build();
    }
}
