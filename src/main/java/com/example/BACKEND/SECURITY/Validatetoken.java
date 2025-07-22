package com.example.BACKEND.SECURITY;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class Validatetoken {

    private Key Key;

    @Value("${jwt-secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationTime;

    @Value("${jwt.refresh-expiration}")
    private long refreshexpiry;

//    private Tokenoutput tokenoutput;

//    @Autowired
//    private Loginfun loginfun;

    @PostConstruct
    public void init() {
//        this.tokenoutput = loginfun.tokrenoutput(); // Call here instead
        this.Key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public boolean validate(String token){
        try {
            Jwts.parserBuilder().setSigningKey(Key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


}

