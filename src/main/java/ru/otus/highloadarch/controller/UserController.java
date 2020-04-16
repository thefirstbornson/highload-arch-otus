package ru.otus.highloadarch.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.highloadarch.domain.User;
import ru.otus.highloadarch.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllAuthors() {
        List<User> users = userService.findAll();
        if (!users.isEmpty()){
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
        Optional<User> user = userService.findById(id);
        return user.<ResponseEntity<?>>map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>("{\"status\":\"not found\"}", HttpStatus.NOT_FOUND));
    }

    @PostMapping(value="/users")
    public ResponseEntity<?> saveUser(@RequestBody User requestBody){
        userService.create(requestBody);
        return new ResponseEntity<>("{\"status\":\"saved\"}", HttpStatus.CREATED);
    }


}
