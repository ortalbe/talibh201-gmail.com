package org.amdocs.elearning.user.service;


import org.amdocs.elearning.user.service.user.User;
import org.amdocs.elearning.user.service.user.UserController;
import org.amdocs.elearning.user.service.user.UserService;
import org.amdocs.elearning.user.service.user.UserType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class UserControllerTest {

    UserService userService = Mockito.mock(UserService.class);

    final UserController controller = new UserController(userService);



    @Test
    public void getUserById_Match(){
        Mockito.when(userService.getUserById(Mockito.anyString())).thenReturn(Optional.of(new User("id", "firstName", "lastName", "middleInitial", UserType.PATRON, LocalDate.now())));

        final ResponseEntity<User> responseEntity = this.controller.getUser("id");
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
        Assert.assertNotNull(responseEntity.getBody());
    }

    @Test
    public void getUserById_NoMatch(){
        Mockito.when(userService.getUserById(Mockito.anyString())).thenReturn(Optional.empty());

        final ResponseEntity<User> responseEntity = this.controller.getUser("id");

        Assert.assertEquals(404, responseEntity.getStatusCodeValue());
        Assert.assertNull(responseEntity.getBody());

    }


}
