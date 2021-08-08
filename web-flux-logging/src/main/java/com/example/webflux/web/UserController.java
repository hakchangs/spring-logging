package com.example.webflux.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @PostMapping
    public User create(@RequestBody User user) {
        log.debug("POST /users");
        return user;
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        log.debug("GET /users/{}", id);
        return User.builder().id(id).name("하이").build();
    }

}
