package com.example.webserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class WebserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebserverApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/a").allowedOrigins("http://localhost:3000");
                registry.addMapping("/t").allowedOrigins("http://localhost:3000");
                registry.addMapping("/r").allowedOrigins("http://localhost:3000");
                registry.addMapping("/p").allowedOrigins("http://localhost:3000");
            }
        };
    }
}
