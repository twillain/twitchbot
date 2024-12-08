package com.motyldrogi.bot.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        // Configure les règles CORS
        registry.addMapping("/**")  // Applique les règles à toutes les routes
                .allowedOrigins("*")  // Autorise les requêtes venant de ton frontend React
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Méthodes autorisées
                .allowedHeaders("*");  // Permet tous les en-têtes
                //.allowCredentials(true);
    }
}

