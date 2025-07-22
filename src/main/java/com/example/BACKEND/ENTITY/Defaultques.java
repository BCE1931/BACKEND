package com.example.BACKEND.ENTITY;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Defaultques {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TEXT")
    private String question;

    private boolean important;

    @Column(columnDefinition = "TEXT")
    private String logic;

    @Column(columnDefinition = "TEXT")
    private String code;

    @Column(columnDefinition = "TEXT")
    private String questioninfo;

    private boolean attempted;

    private boolean work;

    private String topic;

    private String subtopic;

    private String link;

    private LocalDateTime date;
}
