package com.example.BACKEND.SECURITY;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.cors(Customizer.withDefaults())

                .csrf(csrf -> csrf.ignoringRequestMatchers("/oauth/**","/token/**"))

                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/oauth/**","/token/**").permitAll()
                                .requestMatchers("/api/**").
                                authenticated().
                                anyRequest().
                                permitAll())

//                .oauth2Login(oauth2 -> oauth2
//                        .userInfoEndpoint(userinfo -> userinfo.userService(customOAuth2UserService))
//                        .successHandler((request, response, authentication) -> {
//                            response.sendRedirect("http://localhost:5173/tables");
//                        })
//                );

                .oauth2Login().defaultSuccessUrl("http://localhost:5173/middle");


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
//        config.setAllowedHeaders(Arrays.asList("*"));
//        config.setAllowedOriginPatterns(Arrays.asList("http://localhost:5173"));
//        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);  // Allow all routes including /oauth2/**
//
//        return new CorsFilter(source);
//    }
}
