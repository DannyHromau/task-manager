package com.dannyhromau.task.manager.repository;

import com.dannyhromau.task.manager.model.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByAuthor_id(Pageable pageable, UUID authorId);

    List<Task> findByExecutor_id(Pageable pageable, UUID executorId);
}
