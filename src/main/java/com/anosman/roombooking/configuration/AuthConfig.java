package com.anosman.roombooking.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

//@Configuration
//public class AuthConfig implements RepositoryRestConfigurer {
//
//    @Override
//    public void configureRepositoryRestConfiguration(
//            RepositoryRestConfiguration config, CorsRegistry corsRegistry) {
//        corsRegistry.addMapping("/api")
//                .allowedMethods("GET", "POST", "DELETE", "OPTIONS", "PUT", "PATCH")
//                .allowedHeaders("*")
//                .allowedOrigins("http://localhost:4200")
//                .allowCredentials(true);
//    }
//}
