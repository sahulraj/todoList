package com.example.todoList.service;

import com.example.todoList.entity.User;

public interface userService {
    public void createUser(String username, String password);
    public User findUserById(int id);
    public void deleteUser(int id);
}
