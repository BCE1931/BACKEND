package com.example.BACKEND.SERVICES;

import com.example.BACKEND.CONTROLLER.JsonController;
import com.example.BACKEND.ENTITY.*;
import com.example.BACKEND.REPOSOTORIES.*;
import com.example.BACKEND.SECURITY.RefreshTokenStore;
import com.example.BACKEND.SECURITY.TokenGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class Userservice {

    @Autowired
    private Userrepo userrepo;

    @Autowired
    private JsonController jsonController;

    @Autowired
    private RefreshTokenStore refreshTokenStore;

    @Autowired
    private TokenGeneration tokenGeneration;

    @Autowired
    private Aboutokenrepo aboutokenrepo;

    public Outputwrapper register(Registerwrapper registerwrapper){
        Outputwrapper output = new Outputwrapper();
        if(userrepo.existsByUsernameAndProject(registerwrapper.getUsername(), registerwrapper.getProject())){
            output.setExist(true);
            return output;
        }
        User user = new User(registerwrapper);
        System.out.println("register wrapper in userservice" + registerwrapper.getProject());
        user.setDate(LocalDate.now());
        user.setAuth("normal");
        user.setProject(registerwrapper.getProject());
        userrepo.save(user);
        String token = tokenGeneration.generateToken(registerwrapper.getUsername());
        String refreshtoken = tokenGeneration.generaterefreshtoken(registerwrapper.getUsername());
        output.setToken(token);
        output.setRefreshtoken(refreshtoken);
        return output;
    }

    public Outputwrapper login(Loginwrapper loginwrapper){
        Outputwrapper output = new Outputwrapper();
        System.out.println("project name for login wrapper : " + loginwrapper.getProject() );
        if(userrepo.existsByUsernameAndProject(loginwrapper.getUsername(), loginwrapper.getProject())){
            Optional<User> optionalUser =  userrepo.findByUsernameAndProject(loginwrapper.getUsername(),loginwrapper.getProject());
            User user =  optionalUser.get();
            System.out.println("project name for existed and selected user : " + user.getProject());
            if("google".equals(user.getAuth())){
                output.setAuth(false);
                return output;
            }
            else if(user.getPassword().equals(loginwrapper.getPassword())){
                int usertimes = user.getTimes();
                user.setTimes(usertimes+1);
                user.setDate(LocalDate.now());
                userrepo.save(user);
                String token = tokenGeneration.generateToken(loginwrapper.getUsername());
                String refreshtoken = tokenGeneration.generaterefreshtoken(loginwrapper.getUsername());
                output.setToken(token);
                Abouttokens abouttokens = new Abouttokens();
                abouttokens.setUsername(loginwrapper.getUsername());
                abouttokens.setAccesstoken(token);
                abouttokens.setRefreshtoken(refreshtoken);
                abouttokens.setCreationtime(LocalDateTime.now());
                aboutokenrepo.save(abouttokens);
                System.out.println("user logged password is : " + user.getEmail());
                return output;
            }
            else{
                output.setPassword(false);
                return output;
            }
        }

        output.setUsername(false);
        return output;
    }
}
