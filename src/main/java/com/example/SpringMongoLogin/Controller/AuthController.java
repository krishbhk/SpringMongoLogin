package com.example.SpringMongoLogin.Controller;

import com.example.SpringMongoLogin.Model.AuthenticationRequest;
import com.example.SpringMongoLogin.Model.AuthenticationResponse;
import com.example.SpringMongoLogin.Model.UserModel;
import com.example.SpringMongoLogin.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/subs")
    private ResponseEntity<?> subscribeClient(@RequestBody AuthenticationRequest authenticationRequest){
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        UserModel userModel = new UserModel();
        userModel.setUsername(username);
        userModel.setPassword(password);

        try {
            userRepo.save(userModel);
        } catch (Exception e){
            return ResponseEntity.ok(new AuthenticationResponse("Subscription failed "+username));
        }

        return ResponseEntity.ok(new AuthenticationResponse("Subscribed successfully "+username));


    }

    @PostMapping("/auth")
    private ResponseEntity<?> authenticateClient(@RequestBody  AuthenticationRequest authenticationRequest){
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        System.out.println(username+" -> "+password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        } catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.ok(new AuthenticationResponse("Authentication failed "+username));
        }

        return ResponseEntity.ok(new AuthenticationResponse("Authentication done successfully "+username));

    }
}
