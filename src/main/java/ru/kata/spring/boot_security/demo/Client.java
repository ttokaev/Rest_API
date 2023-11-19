package ru.kata.spring.boot_security.demo;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public class Client {

    private final String URL;
    private final RestTemplate restTemplate;
    private HttpHeaders headers;


    public Client(String URL, RestTemplate restTemplate) {
        this.URL = URL;
        this.restTemplate = restTemplate;
        setHeaders();
    }

    public String addUser() {
        User user = new User(3L, "James", "Brown", (byte) 43);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return restTemplate.exchange(URL, HttpMethod.POST, entity, String.class).getBody();
    }

    public String editUser() {
        User user = new User(3L, "Thomas", "Shelby", (byte) 43);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class).getBody();
    }

    public String deleteUser() {
        HttpEntity<User> entity = new HttpEntity<>(null, headers);
        return restTemplate.exchange(URL+"/3", HttpMethod.DELETE, entity, String.class).getBody();
    }

    private void setHeaders() {
        ResponseEntity<List<User>> users = restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        String cookie = users.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        assert cookie != null;
        String sessionId = cookie.substring(cookie.indexOf("=") + 1, cookie.indexOf(";"));
        headers = new HttpHeaders();
        headers.set("Cookie", "JSESSIONID=" + sessionId);
        System.out.println(addUser() + editUser() + deleteUser());
    }
}
