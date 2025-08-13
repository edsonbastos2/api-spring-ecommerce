package com.loja_fake.ecommerce.controller;

import com.loja_fake.ecommerce.controller.dto.CreateUserDto;
import com.loja_fake.ecommerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(path = "/users")
public class Usercontroller {

    private final UserService userService;

    public Usercontroller(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto dto) {
        var user = userService.createUser(dto);

        return ResponseEntity.created(URI.create("/users/"+user.getUserId())).build();
    }
}
