package com.example.BACKEND.REPOSOTORIES;

import com.example.BACKEND.ENTITY.Userstate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Userstaterepo extends JpaRepository<Userstate, String> {
}
