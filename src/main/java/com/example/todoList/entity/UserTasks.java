package com.example.todoList.entity;

import jakarta.persistence.*;
import org.springframework.data.repository.cdi.Eager;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "userTasks")
public class UserTasks {
    @Id
    @Column(name = "user_id")
    private int id;

    @OneToMany(mappedBy = "userTasks", cascade = CascadeType.ALL)
    private List<Task>tasks;

    @JoinColumn(name = "user_id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    private User user;
    public UserTasks()
    {

    }
    public UserTasks(User user)
    {
        this.id = user.getId();
        this.user = user;
        user.setUserTasks(this);
        tasks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    public void addTask(Task task)
    {
        tasks.add(task);
        task.setUserTasks(this);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
