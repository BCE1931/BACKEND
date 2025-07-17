package com.example.BACKEND.ENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Activitydisplay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String statejson;

    private LocalDate date;

    private LocalTime time;

    private int changes;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getStatejson() {
        return statejson;
    }

    public int getChanges() {
        return changes;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStatejson(String statejson) {
        this.statejson = statejson;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setChanges(int changes) {
        this.changes = changes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Activitydisplay(String username, String statejson, LocalDate date, LocalTime time, int changes ){
        this.username = username;
        this.statejson = statejson;
        this.date = date;
        this.time = time;
        this.changes = changes;
    }

    public Activitydisplay(){}
}


