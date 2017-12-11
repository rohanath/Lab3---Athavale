package com.service;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;


import org.json.JSONObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    public List<String> listDir(String username) throws Exception {

        String path = "/Users/rohana/Desktop/SpringBootListDir 2/SpringBootDemo"+username+"/normal";

        File file = new File(path);
        List<String> files = new ArrayList<String>();
        if (file != null) {
            for (String fileName : file.list()) {
                files.add(fileName);
            }
        }
        System.out.println(files);
        return new ArrayList<String>(files);
    }

    public List<String> listStarDir(String username) throws Exception {

        String path = "/Users/rohana/Desktop/SpringBootListDir 2/SpringBootDemo"+username+"/starred";

        File file = new File(path);
        List<String> files = new ArrayList<String>();
        if (file != null) {
            for (String fileName : file.list()) {
                files.add(fileName);
            }
        }
        System.out.println("Inside file service");
        System.out.println(files);
        return new ArrayList<String>(files);
    }

    public JSONObject shareFile(String fileData) {

        JSONObject fileDataJSON = new JSONObject(fileData);
        System.out.println(fileDataJSON);

        String fileName = fileDataJSON.getString("filetoshare");
        String userid = fileDataJSON.getString("username");
        String recipientEmail = fileDataJSON.getString("email");

        System.out.println("fileName: " + fileName + " userid: " + userid + " recipientEmail: " + recipientEmail);

        String dynamicPathToUserFile = "/Users/rohana/Desktop/SpringBootListDir 2/SpringBootDemo"+userid+"/normal/"+fileName;
        /////////////////////////////////////
        JavaMailSender mailSender = new JavaMailSenderImpl();

        ((JavaMailSenderImpl) mailSender).setHost("smtp.gmail.com");
        ((JavaMailSenderImpl) mailSender).setPort(587);

        ((JavaMailSenderImpl) mailSender).setUsername("rohanathavle@gmail.com");
        ((JavaMailSenderImpl) mailSender).setPassword("rona@2011");

        Properties emailProperties = ((JavaMailSenderImpl) mailSender).getJavaMailProperties();
        emailProperties.put("mail.transport.protocol", "smtp");
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
//        emailProperties.put("mail.debug", "true");

        ((JavaMailSenderImpl) mailSender).setJavaMailProperties(emailProperties);
        MimeMessage message = mailSender.createMimeMessage();
        // pass 'true' to the constructor to create a multipart message

        JSONObject returnValues = new JSONObject();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(recipientEmail);
            helper.setCc("rohanathavle@gmail.com");
            helper.setSubject("Sharing file: " + fileName);
            helper.setText("A file has been shared with you.\nPlease find the attachment.");

            FileSystemResource file = new FileSystemResource(new java.io.File(dynamicPathToUserFile));
            helper.addAttachment(fileName, file);

            mailSender.send(message);
            returnValues.put("message", "Shared Successfully");
            returnValues.put("httpStatus", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            returnValues.put("message", "Unable to Share");
            returnValues.put("httpStatus", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        /////////////////////////////////////


        return returnValues;
    }


}