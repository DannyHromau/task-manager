package com.dannyhromau.task.manager.controller;

import com.dannyhromau.task.manager.api.dto.CommentDto;
import com.dannyhromau.task.manager.core.base.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/comment")
@Tag(name = "Comment service", description = "Comment controller")
@ApiResponse(responseCode = "200", description = "Successful operation")
@ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
@ApiResponse(responseCode = "404", description = "Not found")
@ApiResponse(responseCode = "401", description = "Unauthorized")
@ApiResponse(responseCode = "503", description = "Service unavailable")
public interface CommentController extends BaseController<CommentDto> {
    @Override
    @GetMapping("/{id}")
    @Operation(description = "Getting comment by id")
    ResponseEntity<CommentDto> getById(@PathVariable @NonNull UUID id);

    @Override
    @GetMapping("/all")
    @Operation(description = "Getting all comments")
    ResponseEntity<List<CommentDto>> getAll(Pageable page);

    @Override
    @PostMapping(value = "/create")
    @Operation(description = "Creating comment")
    ResponseEntity<CommentDto> create(@RequestBody @NonNull CommentDto dto);

    @Override
    @PutMapping(value = "/update")
    @Operation(description = "Updating comment")
    ResponseEntity<CommentDto> update(@RequestBody @NonNull CommentDto dto);

    @Override
    @DeleteMapping(value = "/{id}")
    @Operation(description = "Deleting comment")
    ResponseEntity<UUID> deleteById(@PathVariable @NonNull UUID id);

    @GetMapping("/by/author")
    @Operation(description = "Getting comments by author")
    ResponseEntity<List<CommentDto>> getCommentsByAuthor(Pageable page, @RequestParam @NonNull UUID authorId);

    @GetMapping("/by/task")
    @Operation(description = "Getting comments belonged task")
    ResponseEntity<List<CommentDto>> getCommentsByTask(Pageable page, @RequestParam @NonNull UUID taskId);
}
