package com.example.BACKEND.ENTITY;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Tokenoutput {

    private String secret;

    private String expiration;

    private String refreshexpiration;

    public String getExpiration() {
        return expiration;
    }

    public String getRefreshexpiration() {
        return refreshexpiration;
    }

    public String getSecret() {
        return secret;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public void setRefreshexpiration(String refreshexpiration) {
        this.refreshexpiration = refreshexpiration;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Tokenoutput(){}
}

