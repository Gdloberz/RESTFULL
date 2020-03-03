package com.front.restclient;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.front.model.User;
import com.front.model.UserDto;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {

    private final String url = "http://localhost:8080/users";

    private RestTemplate restTemplate = new RestTemplate();

    public User getUserToAutorithe(String email){
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            String json = mapper.writeValueAsString(email);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic QWRtaW46MQ==");
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<String>(json, headers);
           return restTemplate.exchange("http://localhost:8080/users/autorize", HttpMethod.POST, request, User.class).getBody();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getAllUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic QWRtaW46MQ==");
        HttpEntity<String> request = new HttpEntity<String>(headers);
        try {
            return restTemplate.exchange(url, HttpMethod.GET, request, String.class).getBody();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void addUser(UserDto user) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            String json = mapper.writeValueAsString(user);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic QWRtaW46MQ==");
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<String>(json, headers);
            restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(UserDto updateUser) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            String json = mapper.writeValueAsString(updateUser);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic QWRtaW46MQ==");
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<String>(json, headers);
            restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(Long id) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            String json = mapper.writeValueAsString(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic QWRtaW46MQ==");
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<String>(json, headers);
            restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
