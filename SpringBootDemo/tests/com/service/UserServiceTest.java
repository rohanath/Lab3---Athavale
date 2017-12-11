package com.service;
import com.AbstractTest;
import com.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Transactional
public class UserServiceTest{

    @Autowired
    private UserService userService;

    @Test
    public void testLogin() {

        String email = "rohan@gmail.com";
        String password = "rohana";
        List<User> users = userService.login(email, password);
        Assert.assertTrue(email.equals(users.get(0).getEmail()));
    }

    @Test
    public void testLoginFail() {

        String email = "rohanp@gmail.com";
        String password = "rohana";
        List<User> users = userService.login(email, password);
        Assert.assertTrue(users==null);
    }

    @Test
    public void testStar() {

        String username = "mark@gmail.com";
        String file = "kryptomake.sql";
        HttpStatus status = userService.starFile(username,file);
        Assert.assertTrue(status==HttpStatus.OK);
    }

    @Test
    public void testDelete() {

        String username = "mark@gmail.com";
        String file = "kryptomake.sql";
        HttpStatus status = userService.deleteFile(username,file);
        Assert.assertTrue(status==HttpStatus.OK);
    }

    @Test
    public void testdeleteStar() {

        String username = "mark@gmail.com";
        String file = "kryptomake.sql";
        HttpStatus status = userService.deletestarFile(username,file);
        Assert.assertTrue(status==HttpStatus.OK);
    }

}
