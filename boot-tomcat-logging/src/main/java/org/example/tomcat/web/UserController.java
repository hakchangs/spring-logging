package org.example.tomcat.web;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public User create(@RequestBody User user) {
        return user;
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return User.builder().id(id).name("하이").build();
    }

}
