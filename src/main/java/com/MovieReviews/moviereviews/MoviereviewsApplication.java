package com.MovieReviews.moviereviews;

import com.MovieReviews.moviereviews.security.models.Role;
import com.MovieReviews.moviereviews.security.models.User;
import com.MovieReviews.moviereviews.security.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class MoviereviewsApplication {
    public static void main(String[] args) {
        SpringApplication.run(MoviereviewsApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));

            userService.saveUser(new User(null, "Emma Watson", "emma", "1234", new ArrayList<>(), null));
            userService.saveUser(new User(null, "Robert Downey Jr.", "robert", "1234", new ArrayList<>(), null));
            userService.saveUser(new User(null, "Angelina Jolie", "angelina", "1234", new ArrayList<>(), null));
            userService.saveUser(new User(null, "Leonardo DiCaprio", "leo", "1234", new ArrayList<>(), null));

            userService.addRoleToUser("emma", "ROLE_USER");
            userService.addRoleToUser("robert", "ROLE_USER");
            userService.addRoleToUser("robert", "ROLE_ADMIN");
            userService.addRoleToUser("angelina", "ROLE_USER");
            userService.addRoleToUser("leo", "ROLE_ADMIN");
        };
    }
}