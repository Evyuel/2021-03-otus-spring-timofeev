package ru.dtimofeev.springapp.anothermicroservice.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class JWTServiceImpl implements JWTService {

    private final String host = "http://localhost:8080/";
    private final String login = "admin";
    private final String password = "admin";
    private final String TOKEN_PREFIX = "Token_best_prefix ";
    private String token;

    @Override
    public String getActualToken() {
        if (token == null || (token != null && isTokenExpired(token))) {
            RestTemplate template = new RestTemplate();
            String credentialsJson = "{\"login\":\"" + login + "\" , \"password\":\"" + password + "\"}";
            String responseEntity = template.postForObject(host + "login", credentialsJson, String.class);
            Matcher matcher = Pattern.compile("^.*\\s(.*)$").matcher(responseEntity);
            if (matcher.find()) {
                token = matcher.group(1);
            }
        }
        return TOKEN_PREFIX + token;
    }

    private Boolean isTokenExpired(String token) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<HttpStatus> responseEntity;
        try {
            responseEntity = template.getForEntity(host + "api/check_token?token=" + token, HttpStatus.class);
            if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
                return false;
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                return true;
            } else e.printStackTrace();
        }
        return null;
    }
}
