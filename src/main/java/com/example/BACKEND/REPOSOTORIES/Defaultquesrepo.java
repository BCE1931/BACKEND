package com.example.BACKEND.REPOSOTORIES;

import com.example.BACKEND.ENTITY.Defaultques;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Defaultquesrepo extends JpaRepository<Defaultques,Long> {
}
