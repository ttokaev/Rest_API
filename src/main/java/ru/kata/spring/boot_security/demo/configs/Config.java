package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.kata.spring.boot_security.demo.Client;

@Configuration
public class Config {

    @Value("${server.url}")
    private String URL;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Client getClient() {
        return new Client(URL, getRestTemplate());
    }
}
