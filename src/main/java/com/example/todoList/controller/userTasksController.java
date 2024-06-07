package com.example.todoList.controller;

import com.example.todoList.entity.Task;
import com.example.todoList.repository.TaskRepository;
import com.example.todoList.service.userTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class userTasksController {
    @Autowired
    private final userTasksService userTasksService;
    public userTasksController(userTasksService userTasksService)
    {
        this.userTasksService = userTasksService;
    }
    @PostMapping("/task/{id}")
    public ResponseEntity<String>addTaskToUser(@PathVariable int id, @RequestBody String taskName)
    {
        userTasksService.addTaskToUser(id, taskName);
        return ResponseEntity.ok("task is added successfully");
    }
    @GetMapping("/tasks/{id}")
    public ResponseEntity<List<String>>getAllTasksOfUser(@PathVariable int id)
    {
        List<Task>tasks = userTasksService.getAllTasks();
        List<String>tasknames = new ArrayList<>();
        for(Task task : tasks)
        {
            if(task.getUserTasks() != null && task.getUserTasks().getId() == id)
            {
                tasknames.add(task.getTaskName());
            }
        }
        return new ResponseEntity<>(tasknames, HttpStatus.OK);
    }
    @GetMapping("/tasks/pending/{id}")
    public ResponseEntity<List<String>>pendingTasks(@PathVariable int id)
    {
        List<String>pendingTasks = userTasksService.getAllPendingTasksOfUser(id);

        return ResponseEntity.ok(pendingTasks);

    }
    @GetMapping("/tasks/finished/{id}")
    public ResponseEntity<List<String>>finishedTasks(@PathVariable int id)
    {
        List<String>finished = userTasksService.getAllFinishedTasksOfUser(id);

        return ResponseEntity.ok(finished);

    }
    @PutMapping("/taskUpdate/{id}")
    public ResponseEntity<String>updateTask(@PathVariable int id, @RequestBody String taskName)
    {
      boolean flag =  userTasksService.finishTask(id, taskName);
      if(flag)
        return ResponseEntity.ok("task is updated");
      else
          return ResponseEntity.ok("we couldnt update the task because of incorrect id or taskname");
    }

}
