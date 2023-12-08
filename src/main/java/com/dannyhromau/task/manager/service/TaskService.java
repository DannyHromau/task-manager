package com.dannyhromau.task.manager.service;

import com.dannyhromau.task.manager.core.base.BaseService;
import com.dannyhromau.task.manager.model.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface TaskService extends BaseService<Task> {
    @Override
    List<Task> getEntities(Pageable pageable);

    @Override
    Task getEntityById(UUID id);

    @Override
    Task addEntity(Task entity);

    @Override
    UUID deleteEntity(UUID id);

    @Override
    Task updateEntity(Task entity);

    List<Task> getEntitiesByAuthorId(Pageable page, UUID authorId);

    List<Task> getEntitiesByExecutorId(Pageable page, UUID executorId);
}
