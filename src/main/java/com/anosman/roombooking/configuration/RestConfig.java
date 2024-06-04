package com.anosman.roombooking.configuration;

import com.anosman.roombooking.entities.Booking;
import com.anosman.roombooking.entities.Room;
import com.anosman.roombooking.entities.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(
            RepositoryRestConfiguration config, CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/data-api/**")
                .allowedMethods("GET", "POST", "DELETE", "OPTIONS", "PUT", "PATCH")
                .allowedHeaders("*")
                .allowedOrigins("http://localhost:4200",
                        "http://room.booking.com",
                        "http://room-booking-client-bucket.s3-website.eu-central-1.amazonaws.com")
                .allowCredentials(true);
        config.exposeIdsFor(User.class, Room.class, Booking.class);
    }
}
