package com.image.service;

import com.image.Dto.input.CredentialsRequestDto;
import com.image.Dto.response.CredentialsResponseDto;
import com.image.repository.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCreds {

    @Autowired
    private CredentialsRepository credentialsRepository;

    public Integer login(CredentialsResponseDto login){
        Optional<CredentialsRequestDto> acc = credentialsRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
        if(acc.isPresent()){
            return 1;
        }
        else
            return 0;
    }

    public void logout(){

    }

    public Integer signup(CredentialsRequestDto signUp){
        System.out.println("saving");
        Optional<CredentialsRequestDto> acc = credentialsRepository.findById(signUp.getEmail());
        if(acc.isPresent()){
            System.out.println("was present email");
            return 0;
        }
        else
        {
            if(!signUp.getCPass().equals(signUp.getPassword())){
                System.out.println("asdasfFAADFQ");
                return 0;
            }
            credentialsRepository.save(signUp);
            System.out.println("saved ho guya");
            return 1;
        }
    }
}
