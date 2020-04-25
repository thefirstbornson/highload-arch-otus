package ru.otus.highloadarch.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.highloadarch.domain.User;
import ru.otus.highloadarch.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
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
    public RedirectView saveUser(@ModelAttribute("register_form") User requestBody){
        User newUser = userService.create(requestBody);
//        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
//        ModelAndView model = new ModelAndView();
//        model.addObject("newUser", new User());
//        model.setViewName("index.html");
        return new RedirectView("/");
    }

    @RequestMapping(value = "/current-user", method = RequestMethod.GET)
    public ResponseEntity<User> currentUserName(Principal principal) {
        User user = userService.findByEmail(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    static class UserIdResponse {
        private String userId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public UserIdResponse(String userId) {
            this.userId = userId;
        }
    }


}
