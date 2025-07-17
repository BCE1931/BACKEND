package com.example.BACKEND.REPOSOTORIES;

import com.example.BACKEND.ENTITY.Activitydisplay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Activityrepo extends JpaRepository<Activitydisplay, Integer> {
    List<Activitydisplay> findByUsername(String username);
}