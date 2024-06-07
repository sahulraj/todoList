package com.example.todoList.repository;

import com.example.todoList.entity.UserTasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTasksRepository extends JpaRepository<UserTasks, Integer> {
}
