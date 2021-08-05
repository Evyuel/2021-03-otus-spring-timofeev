package ru.dtimofeev.springapp.rest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static ru.dtimofeev.springapp.security.SecurityConstants.SECRET;

@RestController
public class JWTController {

    @GetMapping("/api/check_token")
    public ResponseEntity<HttpStatus> checkToken(@RequestParam("token") String token) {
        try {
            JWT.require(Algorithm.HMAC256(SECRET.getBytes()))
                    .build()
                    .verify(token);
        } catch (TokenExpiredException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}