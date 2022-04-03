package com.image.controller;

import com.image.Dto.input.CredentialsRequestDto;
import com.image.Dto.response.CredentialsResponseDto;
import com.image.service.UserCreds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Login {

    @Autowired
    private UserCreds creds;

    @PostMapping("/signin")
    public Integer login(@RequestBody CredentialsResponseDto cred){
        return creds.login(cred);
    }

    public void logout(CredentialsRequestDto cred){
        creds.logout();
    }

    @PostMapping("/signup")
    public Integer signUp(@RequestBody CredentialsRequestDto cred){
        return creds.signup(cred);
    }
}
