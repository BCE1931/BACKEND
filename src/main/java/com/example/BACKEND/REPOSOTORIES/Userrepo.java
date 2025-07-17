package com.example.BACKEND.REPOSOTORIES;

import com.example.BACKEND.ENTITY.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Userrepo extends JpaRepository<User , Integer> {

    boolean existsByEmail(String email);

    boolean existsByUsernameAndProject(String username,String project);

    boolean existsByProject(String project);

    Optional<User> findByUsernameAndProject(String username, String project);

    Optional<User> findByUsername(String username);

    @Query(value = "select username from user where email = :email",nativeQuery = true)
    String findByEmail(String email);

    @Query(value = "select * from user",nativeQuery = true)
    List<User> getall();


}
