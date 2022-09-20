package com.Game.Game.controller;

import com.Game.Game.models.User;
import com.Game.Game.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody User user){
        if(userService.saveUser(user)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

}
