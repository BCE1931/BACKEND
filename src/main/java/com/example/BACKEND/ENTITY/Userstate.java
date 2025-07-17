package com.example.BACKEND.ENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Userstate {

    @Id
    private String username;

    @Lob
    private String stateJson;

    private int changes;

    public Userstate(String username, String stateJson,int changes) {
        this.username = username;
        this.stateJson = stateJson;
        this.changes = changes;
    }

    public Userstate(){}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setStateJson(String stateJson) {
        this.stateJson = stateJson;
    }

    public void setChanges(int changes) {
        this.changes = changes;
    }

    public String getUsername() {
        return username;
    }

    public String getStateJson() {
        return stateJson;
    }

    public int getChanges() {
        return changes;
    }
}
