package com.example.BACKEND.ENTITY;

import lombok.Data;

@Data
public class Outputwrapper {

    private boolean username = true;

    private boolean password = true;

    private boolean auth = true;

    private boolean exist = false;

    private String token;

    private String refreshtoken;

    public void setRefreshtoken(String refreshtoken) {
        this.refreshtoken = refreshtoken;
    }

    public String getRefreshtoken() {
        return refreshtoken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    //    private String username1;

    public void setPassword(boolean password) {
        this.password = password;
    }

    public boolean isExist() {
        return exist;
    }

    public void setUsername(boolean username) {
        this.username = username;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public boolean isAuth() {
        return auth;
    }


    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public boolean isPassword() {
        return password;
    }

    public boolean isUsername() {
        return username;
    }

    public Outputwrapper(boolean auth,boolean password,boolean username,boolean exist){
        this.auth = auth;
        this.password = password;
        this.username = username;
        this.exist = exist;
    }

    public  Outputwrapper(){}
}
