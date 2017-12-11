package com.service;

import com.entity.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.nio.channels.FileChannel;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void changeUser(User user) {
        userRepository.save(user);
    }

    public List<User> login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public List<User> getUserData(String email) {
        return userRepository.findByEmail(email);
    }

    public HttpStatus deleteFile(String username,String filename) {

        File file = new File("/Users/rohana/Desktop/SpringBootListDir 2/SpringBootDemo"+username+"/normal/"+filename);
        /*try {
            delete(file);

        } catch (Exception e) {

        }*/
        if(file.exists()){
            if(file.delete()){
                return HttpStatus.OK;
            }
        }

        return HttpStatus.INTERNAL_SERVER_ERROR;

    }

    public HttpStatus deletestarFile(String username,String filename) {

        File file = new File("/Users/rohana/Desktop/SpringBootListDir 2/SpringBootDemo"+username+"/starred/"+filename);
        /*try {
            delete(file);

        } catch (Exception e) {

        }*/
        if(file.exists()){
            if(file.delete()){
                return HttpStatus.OK;
            }
        }

        return HttpStatus.INTERNAL_SERVER_ERROR;

    }

    public HttpStatus starFile(String username, String filename){

        /*String path = "/Users/rohana/Desktop/SpringBootListDir 2/SpringBootDemo"+username+"/normal/"+filename;
        int bytes = 1024;

        File file = new File(path);
        List<String> files = new ArrayList<String>();
        if (file != null) {
            byte[] bytes1 = path.getBytes();
            Path path1 = Paths.get("/Users/rohana/Desktop/SpringBootListDir 2/SpringBootDemo"+username+"/starred/"+filename);
            Files.write(path1, bytes1);
            return HttpStatus.OK;
        }

        return HttpStatus.INTERNAL_SERVER_ERROR;*/



        File sFile= new File("/Users/rohana/Desktop/SpringBootListDir 2/SpringBootDemo"+username+"/normal/"+filename);
        File dFile= new File("/Users/rohana/Desktop/SpringBootListDir 2/SpringBootDemo"+username+"/starred/"+filename);

        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
                sourceChannel = new FileInputStream(sFile).getChannel();
                destChannel = new FileOutputStream(dFile).getChannel();
                destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
                return HttpStatus.OK;
            }catch(Exception e){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

    }

}