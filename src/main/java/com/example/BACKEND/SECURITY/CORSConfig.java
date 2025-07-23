package com.example.BACKEND.SECURITY;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class CORSConfig implements WebMvcConfigurer {

    @Override

    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")

                .allowedOrigins("http://localhost:5173", "https://<ReactWebAppName>.azurewebsites.net/")
                .allowedMethods("GET", "POST", "PUT")
                .allowedHeaders("*")
                .allowCredentials(true);

    }
}
