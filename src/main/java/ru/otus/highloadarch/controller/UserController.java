package ru.otus.highloadarch.controller;

import liquibase.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.highloadarch.domain.User;
import ru.otus.highloadarch.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers(
            @RequestParam(required = false,defaultValue = "") String firstNamePattern,
                                      @RequestParam(required = false,defaultValue = "") String lastNamePattern
    ) {
        List<User> users;
            users = userService.findUsersUsingPattern(firstNamePattern,lastNamePattern);

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
    public RedirectView saveUser(@ModelAttribute("register_form") User requestBody){
        User newUser = userService.create(requestBody);
        return new RedirectView("/");
    }


    @RequestMapping("/user-page")
    public ModelAndView userPage () {
        return new ModelAndView("user-page");
    }

    @RequestMapping("/registration")
    public ModelAndView registration () {
        return new ModelAndView("registration");
    }

    @RequestMapping(value = "/current-user", method = RequestMethod.GET)
    public ResponseEntity<User> currentUserName(Principal principal) {
        User user = userService.findByEmail(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/generate-users")
    public ResponseEntity<?> generateUsers(@RequestParam Long count) {
        List<User> generatedUsers = userService.generateUsers(count);
        userService.saveAll(generatedUsers);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
