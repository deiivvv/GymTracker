package com.example.apiejercicios.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permitir acceso a todos los endpoints
                .allowedOrigins("http://localhost:8080", "http://gymtracker.duckdns.org:8080") // Permitir solicitudes desde este origen
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permitir estos métodos HTTP
                .allowCredentials(true) // Permitir credenciales (si se están usando)
                .maxAge(3600); // Tiempo de vida del preflight request en segundos
    }
}
