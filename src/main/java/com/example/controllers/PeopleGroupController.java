package com.example.controllers;

import com.example.entities.Dangelo;
import com.example.entities.User;
import com.example.entities.Will;
import com.example.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PeopleGroupController {
    @Autowired
    UserRepository users;

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public List<User> getUsers() {
        return (List<User>) users.findAll();
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public void addUser(@RequestBody User user) {
        users.save(user);
    }

    @RequestMapping(path = "/user", method = RequestMethod.PUT)
    public void updateUser(@RequestBody User user) {
        users.save(user);
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id")int id) {
        users.delete(id);
    }

    @RequestMapping(path = "/djuser/{id}", method = RequestMethod.GET)
    public Dangelo getDangeloUser(@PathVariable("id") int id) {
        RestTemplate restTemplate = new RestTemplate();
        Dangelo dangelo = restTemplate.getForObject("https://secure-retreat-36287.herokuapp.com/user/" + id, Dangelo.class);
        return dangelo;
    }

    @RequestMapping(path = "/wcuser/{id}", method = RequestMethod.POST)
    public void postWillUser(@PathVariable("id") int id) {

        Dangelo dangelo = getDangeloUser(id);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");

        headers.setAll(map);

        Map req_payload = new HashMap();
        req_payload.put("address", dangelo.getAddress());
        req_payload.put("cellphone", dangelo.getPhonenumber());
        req_payload.put("email", dangelo.getEmail());
        req_payload.put("name", dangelo.getName());
        req_payload.put("servicebranch", dangelo.getSsn());

        HttpEntity<?> request = new HttpEntity<>(req_payload, headers);
        String url = "https://pure-lake-90830.herokuapp.com/user";

        ResponseEntity<?> response = new RestTemplate().postForEntity(url, request, String.class);
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
