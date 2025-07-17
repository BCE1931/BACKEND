package com.example.BACKEND.REPOSOTORIES;

import com.example.BACKEND.ENTITY.Toquiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Quiztorepo extends JpaRepository<Toquiz, Integer> {

    @Query(value = "select * from toquiz where username = :username order by datetime desc limit 1",nativeQuery = true)
    Toquiz findUsername(String username);
}
