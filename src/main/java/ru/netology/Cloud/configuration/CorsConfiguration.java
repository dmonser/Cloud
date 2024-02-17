package ru.netology.Cloud.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.netology.Cloud.property.CorsProperties;

@EnableWebSecurity
@RequiredArgsConstructor
public class CorsConfiguration {
    private final CorsProperties corsProperties;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping(corsProperties.getPath())
                        .allowCredentials(corsProperties.isCredentials())
                        .allowedOrigins(corsProperties.getOrigins())
                        .allowedMethods(corsProperties.getMethods());
            }
        };
    }
}
