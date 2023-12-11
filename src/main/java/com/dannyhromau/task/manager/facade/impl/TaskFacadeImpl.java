package com.dannyhromau.task.manager.facade.impl;

import com.dannyhromau.task.manager.api.dto.TaskDto;
import com.dannyhromau.task.manager.core.util.ErrorMessages;
import com.dannyhromau.task.manager.exception.InvalidDataException;
import com.dannyhromau.task.manager.facade.TaskFacade;
import com.dannyhromau.task.manager.mapper.TaskMapper;
import com.dannyhromau.task.manager.model.Task;
import com.dannyhromau.task.manager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TaskFacadeImpl implements TaskFacade {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskDto> getDtos(Pageable pageable) {
        return taskMapper.mapToListTaskDto(taskService.getEntities(pageable));
    }

    @Override
    public TaskDto getDtoByID(UUID id) {
        return taskMapper.mapToTaskDto(taskService.getEntityById(id));
    }

    @Override
    public TaskDto addDto(TaskDto dto) {
        Task task = taskService.addEntity(taskMapper.mapToTask(dto));
        return taskMapper.mapToTaskDto(task);
    }

    @Override
    public UUID deleteDtoById(UUID id) {
        return taskService.deleteEntity(id);
    }

    @Override
    public TaskDto updateDto(TaskDto dto) {
        Task task = taskService.getEntityById(dto.getId());
        taskMapper.updateTaskFromDto(dto, task);
        return taskMapper.mapToTaskDto(taskService.updateEntity(task));
    }

    @Override
    public List<TaskDto> getTasksByAuthorId(Pageable page, UUID authorId) {
        return taskMapper.mapToListTaskDto(taskService.getEntitiesByAuthorId(page, authorId));
    }

    @Override
    public List<TaskDto> getTasksByExecutorId(Pageable page, UUID executorId) {
        return taskMapper.mapToListTaskDto(taskService.getEntitiesByExecutorId(page, executorId));
    }
}
