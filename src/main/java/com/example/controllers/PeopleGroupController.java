package com.example.controllers;

import com.example.entities.User;
import com.example.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@RestController
public class PeopleGroupController {

    @Autowired
    UserRepository users;

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public List<User> getUsers() {
        return (List<User>) users.findAll();
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public void addUser(@RequestBody User user) {users.save(user);
    }

    @RequestMapping(path = "/user", method = RequestMethod.PUT)
    public void updateUser(@RequestBody User user) {
        users.save(user);
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id")int id) {
        users.delete(id);
    }

    @PostConstruct
    public void populator() throws IOException {
        if (users.count() == 0) {
            User user = new User();
            user.setAffiliation("around");
            user.setName("seven");
            user.setAddress("Ball");
            user.setEmailAddress("@");
            user.setPhone("65");
            user.setFlavor("triumphant");
            users.save(user);
        }
    }
}
