package com.example.BACKEND.ENTITY;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Abouttokens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String accesstoken;

    private String refreshtoken;

    private LocalDateTime creationtime;

    public Abouttokens(int id,String username,String accesstoken,String refreshtoken,LocalDateTime creationtime){
        this.id = id;
        this.username = username;
        this.accesstoken = accesstoken;
        this.refreshtoken = refreshtoken;
        this.creationtime = creationtime;
    }

    public Abouttokens(){}

    public String getRefreshtoken() {
        return refreshtoken;
    }

    public void setRefreshtoken(String refreshtoken) {
        this.refreshtoken = refreshtoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreationtime() {
        return creationtime;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public void setCreationtime(LocalDateTime creationtime) {
        this.creationtime = creationtime;
    }
}
