package com.dannyhromau.task.manager.repository;

import com.dannyhromau.task.manager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
}