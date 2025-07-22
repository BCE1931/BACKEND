package com.example.BACKEND.REPOSOTORIES;

import com.example.BACKEND.ENTITY.Topics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Topicsrepo extends JpaRepository<Topics,Long> {

    @Query(value = "select * from topics where topic = :topic and username = :username",nativeQuery = true)
    List<Topics> gettopics(String topic,String username);
}
