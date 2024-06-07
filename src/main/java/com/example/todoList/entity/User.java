package com.example.todoList.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private UserTasks userTasks;
    public User()
    {

    }
    public User(String name, String pass)
    {
        username = name;
        password = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserTasks getUserTasks() {
        return userTasks;
    }

    public void setUserTasks(UserTasks userTasks) {
        this.userTasks = userTasks;
        userTasks.setUser(this);
    }



}
