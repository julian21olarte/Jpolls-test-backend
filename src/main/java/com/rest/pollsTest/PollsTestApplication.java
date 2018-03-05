package com.rest.pollsTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PollsTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(PollsTestApplication.class, args);
    }
        
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins("http://localhost:4200")
                    .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE")
                    .allowCredentials(true)
                    .maxAge(60 * 60 * 24);
            }
        };
    }
}
