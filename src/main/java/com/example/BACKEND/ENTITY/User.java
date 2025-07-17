package com.example.BACKEND.ENTITY;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String email;

    private String password;

    private int times;

    private LocalDate date;

    private String auth;

    private String project;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getAuth() {
        return auth;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public int getTimes() {
        return times;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User(Registerwrapper registerwrapper) {
        this.email = registerwrapper.getEmail();
        this.username = registerwrapper.getUsername();
        this.password = registerwrapper.getPassword();

    }

    public User(){}

}
