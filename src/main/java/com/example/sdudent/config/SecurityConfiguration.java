package com.example.sdudent.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    // Importing necessary classes and libraries
    // Configuration class for Spring Security
    // Enables method-level security annotations globally

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    // Configures security filter chain for HTTP requests
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disables CSRF protection
                .authorizeHttpRequests(
                        request -> request
                                .requestMatchers("/auth/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()

                )
                .sessionManagement(httpSecuritySessionManagementConfigurer -> {
                    httpSecuritySessionManagementConfigurer
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Configures session management to be stateless
                })
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer
                        .authenticationEntryPoint(
                                (request, response, authException) -> {
                                    authException.printStackTrace(); // Prints stack trace of authentication exceptions
                                    response.sendError(HttpStatus.UNAUTHORIZED.value()); // Sends HTTP unauthorized status code
                                }
                        ))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Adds JWT authentication filter before UsernamePasswordAuthenticationFilter

        // Builds and returns the configured security filter chain
        return http.build();
    }
}
