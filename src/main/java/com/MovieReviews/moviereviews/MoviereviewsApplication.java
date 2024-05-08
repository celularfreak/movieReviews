package com.MovieReviews.moviereviews;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;

import static org.springframework.security.config.Customizer.withDefaults;

@SpringBootApplication
public class MoviereviewsApplication {
	public static void main(String[] args) {
		SpringApplication.run(MoviereviewsApplication.class, args);
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests((authz) ->
						authz.anyRequest().permitAll()
				)
				.httpBasic(withDefaults())
				.formLogin((form) ->
						form.loginPage("/login").permitAll()
				)
				.logout((logout) -> logout.permitAll());

		return http.build();
	}
}