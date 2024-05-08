package com.MovieReviews.moviereviews;

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

}