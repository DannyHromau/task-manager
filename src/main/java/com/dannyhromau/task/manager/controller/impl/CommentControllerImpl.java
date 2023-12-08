package com.dannyhromau.task.manager.controller.impl;

import com.dannyhromau.task.manager.api.dto.CommentDto;
import com.dannyhromau.task.manager.controller.CommentController;
import com.dannyhromau.task.manager.facade.CommentFacade;
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
public class CommentControllerImpl implements CommentController {

    private final CommentFacade commentFacade;

    @Override
    public ResponseEntity<CommentDto> getById(@NonNull UUID id) {
        log.info("call getById in comment with id: {}", id);
        return ResponseEntity.ok(commentFacade.getDtoByID(id));
    }

    @Override
    public ResponseEntity<List<CommentDto>> getAll(Pageable page) {
        log.info("call get all comments");
        return ResponseEntity.ok(commentFacade.getDtos(page));
    }

    @Override
    public ResponseEntity<CommentDto> create(@NonNull @RequestBody CommentDto dto) {
        log.info("call create in comment: {}, comment", dto);
        return ResponseEntity.ok(commentFacade.addDto(dto));
    }

    @Override
    public ResponseEntity<CommentDto> update(@NonNull @RequestBody CommentDto dto) {
        log.info("call update in comment: {}, comment", dto);
        return ResponseEntity.ok(commentFacade.updateDto(dto));
    }

    @Override
    public ResponseEntity<UUID> deleteById(@NonNull UUID id) {
        log.info("call delete in comment with id: {}", id);
        return ResponseEntity.ok(commentFacade.deleteDtoById(id));
    }

    @Override
    public ResponseEntity<List<CommentDto>> getCommentsByAuthor(Pageable page, @RequestParam @NonNull UUID authorId) {
        log.info("call get comments with author id: {}", authorId);
        return ResponseEntity.ok(commentFacade.getCommentsByAuthorId(page, authorId));
    }

    @Override
    public ResponseEntity<List<CommentDto>> getCommentsByTask(Pageable page, @RequestParam @NonNull UUID taskId) {
        log.info("call get comments with task id: {}", taskId);
        return ResponseEntity.ok(commentFacade.getCommentsByTaskId(page, taskId));
    }
}
