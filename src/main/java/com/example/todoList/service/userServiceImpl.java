package com.example.todoList.service;

import com.example.todoList.entity.User;
import com.example.todoList.entity.UserTasks;
import com.example.todoList.repository.UserTasksRepository;
import com.example.todoList.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class userServiceImpl implements userService{
    @Autowired
    private final userRepository userRepositoryy;
    @Autowired
    private final UserTasksRepository userTasksRepository;
    public userServiceImpl(userRepository userRepository, UserTasksRepository userTasksRepository)
    {
        userRepositoryy = userRepository;
        this.userTasksRepository = userTasksRepository;
    }

    @Override
    public void createUser(String username, String password) {
        User user =new User(username, password);
        UserTasks userTasks = new UserTasks(user);
        user.setUserTasks(userTasks);
        userTasks.setUser(user);
        userRepositoryy.save(user);
        userTasksRepository.save(userTasks);

    }

    @Override
    public User findUserById(int id) {
        Optional<User> user =  userRepositoryy.findById(id);
        if(user.isPresent() == false)throw new RuntimeException("their is no user with the given id "+ id);
        return user.get();
    }

    @Override
    public void deleteUser(int id) {
        Optional<User>user = userRepositoryy.findById(id);
        if(user.isPresent() == false)throw new RuntimeException("their is no user found with the given id "+id);
        User user1 = user.get();
        userRepositoryy.delete(user1);
    }
}
