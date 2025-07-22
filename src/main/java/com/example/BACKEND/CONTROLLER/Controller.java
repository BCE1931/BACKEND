package com.example.BACKEND.CONTROLLER;

import com.example.BACKEND.ENTITY.Mytopcs;
import com.example.BACKEND.ENTITY.Mywork;
import com.example.BACKEND.ENTITY.Questions;
import com.example.BACKEND.ENTITY.Topics;
import com.example.BACKEND.SECURITY.Validatetoken;
import com.example.BACKEND.SERVICES.Service1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:5173")
public class Controller {

    @Autowired
    private Service1 service1;

    @Autowired
    private Validatetoken validatetoken;

    @GetMapping("/topics/{topic}")
    public ResponseEntity<?> topics(@PathVariable String topic,@RequestHeader("Authorization") String authheader){
        if(!validatetoken.validate(authheader.substring(7))){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("session expired please try again");
        }
        List<Mytopcs> topics = service1.gettopics(topic,validatetoken.extractUsername(authheader.substring(7)));
        return new ResponseEntity(topics, HttpStatus.OK);
    }

    @GetMapping("/questions")
    public ResponseEntity<?> questions(@RequestHeader("Authorization") String authheader){
        if(!validatetoken.validate(authheader.substring(7))){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("session expired please try again");
        }
        List<Questions> questions = service1.getquestions(validatetoken.extractUsername(authheader.substring(7)));
        return new ResponseEntity(questions,HttpStatus.OK);
    }

    @PostMapping("/adddsawork")
    public ResponseEntity<?> add_dsa_work(@RequestBody Mywork mywork,@RequestHeader("Authorization") String authheader){
        if(!validatetoken.validate(authheader.substring(7))){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("session expired please try again");
        }
        mywork.setUsername(validatetoken.extractUsername(authheader.substring(7)));
        return service1.add_dsa_work(mywork);
    }

    @PostMapping("/addotherwork")
    public ResponseEntity<?> add_other_work(@RequestBody Mywork mywork,@RequestHeader("Authorization") String authheader){
        if(!validatetoken.validate(authheader.substring(7))){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("session expired please try again");
        }
        mywork.setUsername(validatetoken.extractUsername(authheader.substring(7)));
        return service1.add_other_work(mywork);
    }

    @GetMapping("/getdsawork")
    public ResponseEntity<?> get_dsa_work(@RequestHeader("Authorization") String authheader){
        if(!validatetoken.validate(authheader.substring(7))){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("session expired please try again");
        }
        return service1.get_dsa_work(validatetoken.extractUsername(authheader.substring(7)));
    }

    @GetMapping("/getwork/{topic}")
    public ResponseEntity<?> get_other_work(@PathVariable String topic,@RequestHeader("Authorization") String authheader){
        if(!validatetoken.validate(authheader.substring(7))){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("session expired please try again");
        }
        return service1.get_other_work(validatetoken.extractUsername(authheader.substring(7)),topic);
    }

//    @GetMapping("/toggleattempt")


}
