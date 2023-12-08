package com.dannyhromau.task.manager.controller.impl;

import com.dannyhromau.task.manager.api.dto.TaskDto;
import com.dannyhromau.task.manager.controller.TaskController;
import com.dannyhromau.task.manager.facade.TaskFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequiredArgsConstructor
public class TaskControllerImpl implements TaskController {

    private final TaskFacade taskFacade;

    @Override
    public ResponseEntity<TaskDto> getById(@NonNull UUID id) {
        log.info("call getById in task with id: {}", id);
        return ResponseEntity.ok(taskFacade.getDtoByID(id));
    }

    @Override
    public ResponseEntity<List<TaskDto>> getAll(Pageable page) {
        log.info("call get all tasks");
        return ResponseEntity.ok(taskFacade.getDtos(page));
    }

    @Override
    public ResponseEntity<TaskDto> create(@NonNull @RequestBody TaskDto dto) {
        log.info("call create in task: {}, task", dto);
        return ResponseEntity.ok(taskFacade.addDto(dto));
    }

    @Override
    public ResponseEntity<TaskDto> update(@NonNull @RequestBody TaskDto dto) {
        log.info("call update in task: {}, task", dto);
        return ResponseEntity.ok(taskFacade.updateDto(dto));
    }

    @Override
    public ResponseEntity<UUID> deleteById(@NonNull UUID id) {
        log.info("call delete in task with id: {}", id);
        return ResponseEntity.ok(taskFacade.deleteDtoById(id));
    }

    @Override
    public ResponseEntity<List<TaskDto>> getTasksByAuthor(Pageable page, @RequestParam @NonNull UUID authorId) {
        log.info("call get tasks with author id: {}", authorId);
        return ResponseEntity.ok(taskFacade.getTasksByAuthorId(page, authorId));
    }

    @Override
    public ResponseEntity<List<TaskDto>> getTasksByExecutor(Pageable page, @RequestParam @NonNull UUID executorId) {
        log.info("call get tasks with executor id: {}", executorId);
        return ResponseEntity.ok(taskFacade.getTasksByExecutorId(page, executorId));
    }
}
