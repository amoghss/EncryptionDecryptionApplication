package com.image.controller;

import com.image.service.ImageEncDec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
public class EncryptionDecryption {

    @Autowired
    public ImageEncDec imageEncDec;

    @GetMapping("/enc")
    public void enc(@RequestHeader String path) throws IOException, NoSuchAlgorithmException {
        System.out.println("dsfa");
        imageEncDec.encrypt(path);
    }

    @GetMapping("/dec")
    public void decryp(@RequestHeader String path) throws NoSuchAlgorithmException, IOException {
        imageEncDec.decrypt(path);
    }
}
