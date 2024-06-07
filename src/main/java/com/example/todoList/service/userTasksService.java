package com.example.todoList.service;

import com.example.todoList.entity.Task;

import java.util.List;

public interface userTasksService {
   // public void createUserTask(int id);
    public void addTaskToUser(int id, String taskName);
    public List<Task> getAllTasksOfUser(int id);
    public List<String> getAllFinishedTasksOfUser(int id);
    public List<String> getAllPendingTasksOfUser(int id);
    public List<Task>getAllTasks();
    public boolean finishTask(int id, String taskName);



}
