package com.example.BACKEND.ENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Toquiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String token;
    private String username;
    private boolean login;
    private String email;
    private LocalDateTime datetime;

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public boolean isLogin() {
        return login;
    }

    public String getToken() {
        return token;
    }

    public Toquiz(String username,String token,boolean login,String email){
        this.username = username;
        this.token = token;
        this.login = login;
        this.email = email;
    }

    public Toquiz(){}
}

