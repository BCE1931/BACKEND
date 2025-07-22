package com.example.BACKEND.REPOSOTORIES;

import com.example.BACKEND.ENTITY.Mytopcs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Mytopicsrepo extends JpaRepository<Mytopcs,Long> {

    boolean existsByUsernameAndTopicAndSubtopic(String username, String topic, String subtopic);

    Mytopcs findByUsernameAndTopicAndSubtopic(String username, String topic, String subtopic);


    boolean existsByTopicAndUsername(String topic, String username);

    List<Mytopcs> findByTopicAndUsername(String topic, String username);

}
