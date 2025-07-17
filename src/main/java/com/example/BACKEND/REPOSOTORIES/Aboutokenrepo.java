package com.example.BACKEND.REPOSOTORIES;

import com.example.BACKEND.ENTITY.Abouttokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Aboutokenrepo extends JpaRepository<Abouttokens,Integer> {

    @Query(value = "select username from abouttokens where refreshtoken =:oldtoken order by creationtime desc limit 1",nativeQuery = true)
    String getusername(String oldtoken);
}
