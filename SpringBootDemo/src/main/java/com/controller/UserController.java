package com.controller;

import com.entity.User;
import com.service.FileService;
import com.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;

@Controller    // This means that this class is a Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/users") // This means URL's start with /demo (after Application path)
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @PostMapping(path = "/doSignUp", consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public ResponseEntity<?> addNewUser(@RequestBody User user) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        userService.addUser(user);
        System.out.println("Saved");
        JSONObject jsonObject = new JSONObject(user);
        System.out.println(jsonObject);
        String username = jsonObject.getString("email");
        new File("/Users/rohana/Desktop/SpringBootListDir 2/SpringBootDemo"+username).mkdir();
        new File("/Users/rohana/Desktop/SpringBootListDir 2/SpringBootDemo"+username+"/normal").mkdir();
        new File("/Users/rohana/Desktop/SpringBootListDir 2/SpringBootDemo"+username+"/starred").mkdir();
        new File("/Users/rohana/Desktop/SpringBootListDir 2/SpringBootDemo"+username+"/GroupsCreated").mkdir();
        new File("/Users/rohana/Desktop/SpringBootListDir 2/SpringBootDemo"+username+"/GroupsShared").mkdir();
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @PostMapping(path = "/changeUserData", consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public ResponseEntity<?> changeUserData(@RequestBody User user) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        userService.changeUser(user);
        System.out.println("Saved");
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @PostMapping(path = "/download", consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public ResponseEntity<InputStreamResource> downloadFile1() throws IOException {

        String FILE_PATH = "D:/e-Books/jsp_tutorial.pdf";

        File file = new File(FILE_PATH);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=" + file.getName())
                .contentType(MediaType.APPLICATION_PDF).contentLength(file.length())
                .body(resource);
    }


    @PostMapping(path = "/getFiles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listDir(@RequestBody String user) throws Exception {
        JSONObject jsonObject = new JSONObject(user);
        System.out.println(jsonObject);
        return new ResponseEntity(fileService.listDir(jsonObject.getString("username")), HttpStatus.OK);
    }

    @PostMapping(path = "/getstarFiles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listStarDir(@RequestBody String user) throws Exception {
        JSONObject jsonObject = new JSONObject(user);
        System.out.println(jsonObject);
        return new ResponseEntity(fileService.listStarDir(jsonObject.getString("username")), HttpStatus.OK);
    }

    @PostMapping(path = "/files", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> storeFile(@RequestBody HttpServletRequest file) throws Exception {
        System.out.println("Rohan");
        JSONObject jsonObject = new JSONObject(file);
        System.out.println(jsonObject);
        return new ResponseEntity(fileService.listDir(jsonObject.getString("username")), HttpStatus.OK);
    }

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody String user, HttpSession session) {
        JSONObject jsonObject = new JSONObject(user);
        session.setAttribute("name", jsonObject.getString("username"));
        return new ResponseEntity(userService.login(jsonObject.getString("username"), jsonObject.getString("password")), HttpStatus.OK);
    }

    @PostMapping(path = "/share", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> shareFile(@RequestBody String fileData) {

        System.out.println("shareFile:" + fileData);
        JSONObject shareFileServiceReturnValues = fileService.shareFile(fileData);
        return new ResponseEntity(shareFileServiceReturnValues.getString("message"), (HttpStatus) shareFileServiceReturnValues.get("httpStatus"));
    }

    @PostMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteFile(@RequestBody String fileData) {

        System.out.println("shareFile:" + fileData);
        JSONObject jsonObject = new JSONObject(fileData);
        return new ResponseEntity(userService.deleteFile(jsonObject.getString("username"), jsonObject.getString("filename")), HttpStatus.OK);
    }

    @PostMapping(path = "/starfile", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> starFile(@RequestBody String fileData) {

        System.out.println("shareFile:" + fileData);
        JSONObject jsonObject = new JSONObject(fileData);
        return new ResponseEntity(userService.starFile(jsonObject.getString("username"), jsonObject.getString("filename")), HttpStatus.OK);
    }

    @PostMapping(path = "/deletestarfile", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deletestarFile(@RequestBody String fileData) {

        System.out.println("shareFile:" + fileData);
        JSONObject jsonObject = new JSONObject(fileData);
        return new ResponseEntity(userService.deletestarFile(jsonObject.getString("username"), jsonObject.getString("filename")), HttpStatus.OK);
    }

    @PostMapping(path = "/getUserData", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserData(@RequestBody String user, HttpSession session) {
        JSONObject jsonObject = new JSONObject(user);
        session.setAttribute("name", jsonObject.getString("username"));
        return new ResponseEntity(userService.getUserData(jsonObject.getString("username")), HttpStatus.OK);
    }

    @PostMapping(value = "/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> logout(HttpSession session) {
        System.out.println(session.getAttribute("name"));
        session.invalidate();
        return new ResponseEntity(HttpStatus.OK);
    }
}