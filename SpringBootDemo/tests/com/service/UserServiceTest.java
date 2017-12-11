package com.service;
import com.AbstractTest;
import com.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class UserServiceTest extends AbstractTest {

    @Autowired
    private UserService userService;

    @Test
    public void testLogin() {

        String email = "rohan@gmail.com";
        String password = "rohana";
        List<User> users = userService.login(email, password);
        Assert.assertTrue(email.equals(users.get(0).getEmail()));
    }

    /*@Test
    public void testAddUser() {
        User user = new User();
        user.setFirstname("Rohan");
        user.setLastname("Athavale");
        user.setEmail("rohan.athavale@sjsu.edu");
        user.setPassword("rohana");

        User addedUser = userService.addUser(user);
        System.out.println(addedUser != null);
        System.out.println(addedUser.getEmail());

        Assert.assertTrue(user.getEmail().equals(addedUser.getEmail()));
    }*/
}
