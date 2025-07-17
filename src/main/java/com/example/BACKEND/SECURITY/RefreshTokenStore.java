package com.example.BACKEND.SECURITY;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RefreshTokenStore {
    private Map<String,String> store = new ConcurrentHashMap<>();

    public void store(String token,String username){
        store.put(token,username);
    }

    public String getusername(String token){
        return store.get(token);
    }

    public void remove(String token){
        store.remove(token);
    }
}
