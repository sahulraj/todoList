package com.example.todoList.controller;

import com.example.todoList.entity.User;
import com.example.todoList.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class userController {
    @Autowired
    private userService userService;
    public userController(userService userService1)
    {
        userService = userService1;
    }
    @PostMapping("/User")
    ResponseEntity<String>createUser(@RequestBody User user)
    {
        userService.createUser(user.getUsername(), user.getPassword());
        return ResponseEntity.ok("user created sucessfully");
    }
    @DeleteMapping("/User/{id}")
    ResponseEntity<String>deleteUser(@PathVariable int id)
    {
        if(userService.findUserById(id) == null)throw new RuntimeException("user with given id dont exist");
        userService.deleteUser(id);
        return ResponseEntity.ok("user deleted successfully");

    }
}
