package com.dannyhromau.task.manager.service.impl;

import com.dannyhromau.task.manager.model.Task;
import com.dannyhromau.task.manager.service.TaskService;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public class TaskServiceImpl implements TaskService {
    @Override
    public List<Task> getEntities(Pageable pageable) {
        return null;
    }

    @Override
    public Task getEntityById(UUID id) {
        return null;
    }

    @Override
    public Task addEntity(Task entity) {
        return null;
    }

    @Override
    public UUID deleteEntity(UUID id) {
        return null;
    }

    @Override
    public Task updateEntity(Task entity) {
        return null;
    }
}
