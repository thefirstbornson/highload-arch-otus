package ru.otus.highloadarch.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.otus.highloadarch.domain.User;
import ru.otus.highloadarch.service.UserCrudService;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserCrudService userCrudService;

    public UserController(UserCrudService userCrudService) {
        this.userCrudService = userCrudService;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllAuthors() {
        List<User> users = userCrudService.findAll();
        if (!users.isEmpty()){
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
        Optional<User> user = userCrudService.findById(id);
        return user.<ResponseEntity<?>>map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>("{\"status\":\"not found\"}", HttpStatus.NOT_FOUND));
    }

    @PostMapping(value="/users")
    public ResponseEntity<?> saveUser(@RequestBody User requestBody){
        userCrudService.create(requestBody);
        return new ResponseEntity<>("{\"status\":\"saved\"}", HttpStatus.CREATED);
    }


}
