package com.loja_fake.ecommerce.controller;

import com.loja_fake.ecommerce.controller.dto.CreateUserDto;
import com.loja_fake.ecommerce.entities.UsersEntity;
import com.loja_fake.ecommerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

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

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UsersEntity> findById(@PathVariable("userId") UUID userId) {
        var user = userService.findbyId(userId);

        return user.isPresent() ?
                ResponseEntity.ok(user.get()) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<UsersEntity> deleteById(@PathVariable("userId") UUID userId) {
        var deleted = userService.deleteById(userId);

        return deleted ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}
