package com.image.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

@Service
public class ImageEncDec {

    private final Key key;

    public ImageEncDec() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        key = keyGenerator.generateKey();
    }

    public byte[] getFile(String filepath) {

        File f = new File(filepath);
        InputStream is = null;
        try {
            is = new FileInputStream(f);
        } catch (FileNotFoundException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        byte[] content = null;
        try {
            content = new byte[is.available()];
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            is.read(content);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return content;
    }

    public byte[] encryptPdfFile(Key key, byte[] content) {
        Cipher cipher;
        byte[] encrypted = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encrypted = cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrypted;

    }

    public byte[] decryptPdfFile(Key key, byte[] textCryp) {
        Cipher cipher;
        byte[] decrypted = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            decrypted = cipher.doFinal(textCryp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return decrypted;
    }

    public void saveFile(byte[] bytes, String filePath) throws IOException {

        File f= new File(filePath);
        f.delete();

        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(bytes);
        fos.close();

    }

    public String encrypt(String filepath) throws IOException, NoSuchAlgorithmException {

        byte[] content = getFile(filepath);
        byte[] encrypted = encryptPdfFile(key, content);

        saveFile(encrypted, filepath);
        System.out.println("Done");

        System.out.println(key);
        System.out.println(key.toString());
        return key.toString();
    }

    public String decrypt(String filename) throws NoSuchAlgorithmException, IOException {

        byte[] encrypted = getFile(filename);

        byte[] decrypted = decryptPdfFile(key, encrypted);
        System.out.println(decrypted);

        saveFile(decrypted, filename);
        return "s";
    }
}