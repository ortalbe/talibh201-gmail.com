package org.amdocs.elearning.user.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable final String id) {
        final Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            return new ResponseEntity<User>(userOptional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody @Valid final UserDetails user) {

        final User createdUser = this.userService.createUser(user);
        return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{id}" , method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable final String id ,@RequestBody @Valid final UserDetails user) {

        final User createdUser = this.userService.updateUser(id,user);
        return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable final String id) {

        final Optional<User> user = this.userService.getUserById(id);
        if (!user.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            this.userService.deleteUser(user.get().getId());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

}
