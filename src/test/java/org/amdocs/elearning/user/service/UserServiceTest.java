package org.amdocs.elearning.user.service;

import org.amdocs.elearning.user.service.ApplicationConfig;
import org.amdocs.elearning.user.service.user.User;
import org.amdocs.elearning.user.service.user.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class UserServiceTest {

    @Autowired
    private  UserService userService;
   // private final UserService userService = new UserService();

    @Test
    public void getUser_Match()
    {
        final Optional<User> user = userService.getUserById("1");

        Assert.assertEquals(true, user.isPresent());

    }

    @Test
    public void getUser_NoMatch()
    {
        final Optional<User> user = userService.getUserById("100000");

        Assert.assertEquals(false, user.isPresent());
    }
}
