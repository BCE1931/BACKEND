package com.example.BACKEND.REPOSOTORIES;

import com.example.BACKEND.ENTITY.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Questionsrepo extends JpaRepository<Questions,Long> {

    List<Questions> findByUsername(String username);
}
