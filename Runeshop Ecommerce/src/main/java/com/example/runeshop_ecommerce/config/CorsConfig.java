package com.example.runeshop_ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "https://localhost:5173",
                        "http://localhost:5173",
                        "https://api.mercadopago.com",
                        "https://www.mercadopago.com.ar",
                        "https://www.mercadopago.com",
                        "ID NGROK"
                )
                .allowedMethods("GET", "POST", "PUT", "PATCH", "OPTIONS")
                .allowedHeaders(
                        "Authorization",
                        "Content-Type",
                        "X-Requested-With",
                        "Accept",
                        "Origin",
                        "Access-Control-Request-Method",
                        "Access-Control-Request-Headers"
                )
                .exposedHeaders(
                        "Authorization",
                        "Content-Type",
                        "Content-Disposition",
                        "Location"
                )
                .allowCredentials(true)
                .maxAge(3600);
    }
}

