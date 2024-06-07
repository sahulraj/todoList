package com.example.todoList.service;

import com.example.todoList.entity.Task;
import com.example.todoList.entity.User;
import com.example.todoList.entity.UserTasks;
import com.example.todoList.repository.TaskRepository;
import com.example.todoList.repository.UserTasksRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class userTasksServiceImpl implements userTasksService {
    @Autowired
    private final UserTasksRepository userTasksRepository;
    @Autowired
    private final TaskRepository taskRepository;
    @Autowired
    private final userService userService;
    public userTasksServiceImpl(UserTasksRepository userTasksRepository, TaskRepository taskRepository, userService userService)
    {
        this.userTasksRepository = userTasksRepository;
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    //@Override
//    public void createUserTask(int id) {
//        User user =  userService.findUserById(id);
//
//        UserTasks userTasks = new UserTasks(user);
//        userTasksRepository.save(userTasks);
//
//
//    }

    @Override
    //@Transactional
    public void addTaskToUser(int id, String taskName) {
        User user = userService.findUserById(id);
        UserTasks userTasks = user.getUserTasks();
        Task task = new Task(taskName, userTasks);
        taskRepository.save(task);
        userTasks.addTask(task);


    }


    @Override
    public List<Task> getAllTasksOfUser(int id) {
        Optional<UserTasks>userTasks = userTasksRepository.findById(id);
        if(userTasks.isPresent() == false)throw new RuntimeException("their is no user with the given id");
        UserTasks userTasks1 = userTasks.get();
        return userTasks1.getTasks();
    }

    @Override
    public List<String> getAllFinishedTasksOfUser(int id) {
        List<String>finished = new ArrayList<>();
        List<Task>tasks = userTasksRepository.findById(id).get().getTasks();
        for(Task task : tasks)
        {
            if(task.getFinished())
                finished.add(task.getTaskName());
        }
        return finished;
    }

    @Override
    public List<String> getAllPendingTasksOfUser(int id) {
        List<String>pending = new ArrayList<>();
        List<Task>tasks = userTasksRepository.findById(id).get().getTasks();
        for(Task task : tasks)
        {
            if(!task.getFinished())
                pending.add(task.getTaskName());
        }
        return pending;

    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public boolean finishTask(int id, String taskName) {
        UserTasks userTasks = userService.findUserById(id).getUserTasks();
        boolean flag = false;
        List<Task>tasks = userTasks.getTasks();
        for(Task task1 : tasks)
        {
            if(Objects.equals(task1.getTaskName(), taskName))
            {
                task1.setFinished(true);
                taskRepository.save(task1);
                return true;
            }
        }
        return flag;

    }

}
