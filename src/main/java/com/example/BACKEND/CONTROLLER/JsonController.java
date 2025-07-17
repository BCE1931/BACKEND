package com.example.BACKEND.CONTROLLER;

import com.example.BACKEND.ENTITY.Abouttokens;
import com.example.BACKEND.ENTITY.Activitydisplay;
import com.example.BACKEND.ENTITY.Userstate;
import com.example.BACKEND.REPOSOTORIES.Aboutokenrepo;
import com.example.BACKEND.REPOSOTORIES.Activityrepo;
import com.example.BACKEND.REPOSOTORIES.Userstaterepo;
import com.example.BACKEND.SECURITY.TokenGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/token")
@CrossOrigin(origins = "http://localhost:5173")
public class JsonController {

    @Autowired
    private TokenGeneration tokenGeneration;

    @Autowired
    private Userstaterepo userstaterepo;

    @Autowired
    private Activityrepo activityrepo;

//    @Autowired
//    private RefreshTokenStore refreshTokenStore;

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

    public String getToken(){
        return token;
    }

    public void settoken(String token){
        this.token = token;
    }

    @GetMapping("/tokengen/{username}")
    public ResponseEntity<?> tokengenlogin(@PathVariable String username){
        System.out.println("Token generation requested for: " + username);
        String token = tokenGeneration.generateToken(username);
        String refreshtoken = tokenGeneration.generaterefreshtoken(username);
//        refreshTokenStore.store(refreshtoken,username);
        settoken(token);
//        System.out.println("hey this is n username for logged function : " + refreshTokenStore.getusername(refreshtoken));
        setRefreshtoken(refreshtoken);
        Abouttokens abouttokens = new Abouttokens();
        abouttokens.setUsername(username);
        abouttokens.setAccesstoken(token);
        abouttokens.setRefreshtoken(refreshtoken);
        abouttokens.setCreationtime(LocalDateTime.now());
        aboutokenrepo.save(abouttokens);
        return ResponseEntity.ok(Map.of("token",token,"refreshtoken",refreshtoken));
    }

    //    @GetMapping("/getdata")
    @PostMapping("/getdata")
    public ResponseEntity<?> getState(@RequestHeader("Authorization") String authheader){
        String token = authheader.substring(7);
        if(!tokenGeneration.validchecker(token)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Expired token");
        }
        String username = tokenGeneration.extractUsername(token);
        Optional<Userstate> state = userstaterepo.findById(username);
        return ResponseEntity.ok(state.map(Userstate::getStateJson).orElse("{}"));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String,String> body){
        String oldtoken = body.get("refreshtoken");

        if(oldtoken == null || !tokenGeneration.validchecker(oldtoken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Refresh Token");
        }

        String username = aboutokenrepo.getusername(oldtoken);

//        String username = refreshTokenStore.getusername(oldtoken);
        System.out.println("hey this is an username for previous token : " + username);
//        refreshTokenStore.remove(oldtoken);
        String newcode = tokenGeneration.generateToken(username);
        String refreshcode = tokenGeneration.generaterefreshtoken(username);
//        refreshTokenStore.store(refreshcode,username);
        Abouttokens abouttokens = new Abouttokens();
        abouttokens.setCreationtime(LocalDateTime.now());
        abouttokens.setRefreshtoken(refreshcode);
        abouttokens.setUsername(username);
        abouttokens.setAccesstoken(newcode);
        aboutokenrepo.save(abouttokens);
//        System.out.println("hey this is newly updated token : " + refreshTokenStore.getusername(refreshcode));
        return ResponseEntity.ok(Map.of("token",newcode,"refreshtoken",refreshcode));
    }

    @PostMapping("/savedata")
    public ResponseEntity<?> savestate(@RequestHeader("Authorization") String authheader,@RequestBody String stateJson){
        String token = authheader.substring(7);
        String username = tokenGeneration.extractUsername(token);
        Optional<Userstate> userstate = userstaterepo.findById(username);
        userstaterepo.save(new Userstate(username,stateJson,userstate.get().getChanges()+1));

        Activitydisplay activitydisplay = new Activitydisplay();
        activitydisplay.setUsername(username);
        activitydisplay.setStatejson(stateJson);
        activitydisplay.setChanges(userstate.get().getChanges() );
        activitydisplay.setTime(LocalTime.now());
        activitydisplay.setDate(LocalDate.now());

        activityrepo.save(activitydisplay);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/activity/{username}")
    public List<Activitydisplay> activity(@PathVariable String username){
        List<Activitydisplay> activitydisplay = activityrepo.findByUsername(username);
        return activitydisplay;
    }

}
