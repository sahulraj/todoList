package com.example.todoList.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "taskname")
    private String taskName;
    @Column(name = "is_finished")
    private Boolean isFinished = false;
    @ManyToOne
    @JoinColumn(name = "user_tasks_id")//foreign key
    private UserTasks userTasks;
    public Task()
    {

    }

    public Task(String taskname, UserTasks userTasks)
    {
        taskName = taskname;
        this.userTasks = userTasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public UserTasks getUserTasks() {
        return userTasks;
    }

    public void setUserTasks(UserTasks userTasks) {
        this.userTasks = userTasks;
    }
}
