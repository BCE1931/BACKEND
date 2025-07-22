package com.example.BACKEND.CONTROLLER;

import com.example.BACKEND.ENTITY.Abouttokens;
import com.example.BACKEND.REPOSOTORIES.Aboutokenrepo;
import com.example.BACKEND.SECURITY.TokenGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/token")
@CrossOrigin(origins = "http://localhost:5173")
public class JsonController {

    @Autowired
    private TokenGeneration tokenGeneration;

    @Autowired
    private Aboutokenrepo aboutokenrepo;

    private String token;

    private String refreshtoken;

    public String getRefreshtoken() {
        return refreshtoken;
    }

    public void setRefreshtoken(String refreshtoken) {
        this.refreshtoken = refreshtoken;
    }

    public String getToken() {
        return token;
    }

    public void settoken(String token) {
        this.token = token;
    }

    @GetMapping("/tokengen/{username}")
    public ResponseEntity<?> tokengenlogin(@PathVariable String username) {
        System.out.println("Token generation requested for: " + username);
        String token = tokenGeneration.generateToken(username);
        String refreshtoken = tokenGeneration.generaterefreshtoken(username);
        settoken(token);
        setRefreshtoken(refreshtoken);
        Abouttokens abouttokens = new Abouttokens();
        abouttokens.setUsername(username);
        abouttokens.setAccesstoken(token);
        abouttokens.setRefreshtoken(refreshtoken);
        abouttokens.setCreationtime(LocalDateTime.now());
        aboutokenrepo.save(abouttokens);
        return ResponseEntity.ok(Map.of("token", token, "refreshtoken", refreshtoken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> body) {
        String oldtoken = body.get("refreshtoken");

        if (oldtoken == null || !tokenGeneration.validchecker(oldtoken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Refresh Token");
        }
        String username = aboutokenrepo.getusername(oldtoken);
        System.out.println("hey this is an username for previous token : " + username);
        String newcode = tokenGeneration.generateToken(username);
        String refreshcode = tokenGeneration.generaterefreshtoken(username);
        Abouttokens abouttokens = new Abouttokens();
        abouttokens.setCreationtime(LocalDateTime.now());
        abouttokens.setRefreshtoken(refreshcode);
        abouttokens.setUsername(username);
        abouttokens.setAccesstoken(newcode);
        aboutokenrepo.save(abouttokens);
        return ResponseEntity.ok(Map.of("token", newcode, "refreshtoken", refreshcode));
    }
}