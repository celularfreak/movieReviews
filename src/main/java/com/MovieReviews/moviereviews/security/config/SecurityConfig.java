package com.MovieReviews.moviereviews.security.config;

import com.MovieReviews.moviereviews.security.filters.CustomAuthenticationFilter;
import com.MovieReviews.moviereviews.security.filters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private AuthenticationManagerBuilder authManagerBuilder;

    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authManagerBuilder.getOrBuild());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/api/login/**").permitAll()
                .requestMatchers("/error/**").permitAll()

                .requestMatchers("/v3/api-docs/**").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()

                .requestMatchers(GET, "/api/users").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(POST, "/api/users").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers(PUT, "/api/users").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers(DELETE, "/api/users").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers("/api/roles/**").hasAnyAuthority("ROLE_ADMIN")

                .requestMatchers(GET, "/films/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(POST, "/films/add").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(PUT, "/films/*").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(DELETE,"/films/*").hasAnyAuthority("ROLE_ADMIN")

                .requestMatchers(GET, "/tv-series/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(POST, "/tv-series/add").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(PUT, "/tv-series/*").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(DELETE, "/tv-series/*").hasAnyAuthority("ROLE_ADMIN")

                .requestMatchers(GET, "/animes/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(POST, "/animes/add").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(PUT, "/animes/*").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(DELETE, "/animes/*").hasAnyAuthority("ROLE_ADMIN")

                .requestMatchers(GET, "/miniseries/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(POST, "/miniseries/add").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(PUT, "/miniseries/*").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(DELETE, "/miniseries/*").hasAnyAuthority("ROLE_ADMIN")

                .requestMatchers(GET, "/reviews/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(PUT, "/reviews/*").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(DELETE, "/reviews/**").hasAnyAuthority("ROLE_ADMIN")

                .requestMatchers(GET, "/tv-series-reviews/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(POST, "/tv-series-reviews/add").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(PUT, "/tv-series-reviews/*").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(DELETE, "/tv-series-reviews/*").hasAnyAuthority("ROLE_ADMIN")

                .requestMatchers(GET, "/film-reviews/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(POST, "/film-reviews/add").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(PUT, "/film-reviews/*").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(DELETE, "/film-reviews/*").hasAnyAuthority("ROLE_ADMIN")

                .anyRequest().authenticated());
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}