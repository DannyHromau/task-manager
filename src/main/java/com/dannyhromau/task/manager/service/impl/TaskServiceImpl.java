package com.dannyhromau.task.manager.service.impl;

import com.dannyhromau.task.manager.core.util.ErrorMessages;
import com.dannyhromau.task.manager.core.util.TokenUtil;
import com.dannyhromau.task.manager.exception.EntityNotfoundException;
import com.dannyhromau.task.manager.exception.ForbiddenException;
import com.dannyhromau.task.manager.exception.InvalidDataException;
import com.dannyhromau.task.manager.model.Task;
import com.dannyhromau.task.manager.repository.TaskRepository;
import com.dannyhromau.task.manager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TokenUtil tokenUtil;
    private static final String ENTITY_NOT_FOUND_MESSAGE = ErrorMessages.ENTITY_NOT_FOUND_MESSAGE.label;
    private static final String INCORRECT_DATA_MESSAGE = ErrorMessages.INCORRECT_DATA_MESSAGE.label;
    private static final String FORBIDDEN_STATUS_MESSAGE = ErrorMessages.FORBIDDEN_STATUS_MESSAGE.label;

    @Override
    public List<Task> getEntities(Pageable pageable) {
        return taskRepository.findAll(pageable).toList();
    }

    @Override
    public Task getEntityById(UUID id) {
        return checkValidData(id);
    }

    @Override
    public Task addEntity(Task task) {
        if (task.getId() != null) {
            throw new InvalidDataException(INCORRECT_DATA_MESSAGE);
        } else {
            task.setCreatedOn(ZonedDateTime.now());
            return taskRepository.save(task);
        }
    }

    @Override
    public UUID deleteEntity(UUID id) {
        Task task = checkValidData(id);
        if (tokenUtil.getUserDetails().getId().equals(task.getAuthor_id())) {
            taskRepository.deleteById(task.getId());
        } else {
            throw new ForbiddenException(FORBIDDEN_STATUS_MESSAGE);
        }
        return id;
    }

    @Override
    public Task updateEntity(Task task) {
        task = checkValidData(task.getId());
        if (tokenUtil.getUserDetails().getId().equals(task.getAuthor_id()) ||
                tokenUtil.getUserDetails().getId().equals(task.getExecutor_id())) {
            return taskRepository.save(task);
        } else {
            throw new ForbiddenException(FORBIDDEN_STATUS_MESSAGE);
        }
    }

    public List<Task> getEntitiesByStatus(Task.Status status, Pageable pageable){
        return taskRepository.findByStatus(status);
    }

    public List<Task> getEntitiesByPriority(Task.Priority priority, Pageable pageable){
        return taskRepository.findByPriority(priority);
    }

    private Task checkValidData(UUID id) {
        Optional<Task> userOpt = taskRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new EntityNotfoundException(String.format(ENTITY_NOT_FOUND_MESSAGE, id));
        } else {
            return userOpt.get();
        }
    }
}
