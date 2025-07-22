package com.example.BACKEND.REPOSOTORIES;

import com.example.BACKEND.ENTITY.Mywork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Myworkrepo extends JpaRepository<Mywork,Long> {
    List<Mywork> findByUsernameAndTopic(String username, String dsa);
}
