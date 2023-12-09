package com.dannyhromau.task.manager.controller;

import com.dannyhromau.task.manager.api.dto.TaskDto;
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
@RequestMapping("/api/v1/task")
@Tag(name = "Task service", description = "Task controller")
@ApiResponse(responseCode = "200", description = "Successful operation")
@ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
@ApiResponse(responseCode = "404", description = "Not found")
@ApiResponse(responseCode = "401", description = "Unauthorized")
@ApiResponse(responseCode = "503", description = "Service unavailable")
public interface TaskController extends BaseController<TaskDto> {
    @Override
    @GetMapping("/{id}")
    @Operation(description = "Getting task by id")
    ResponseEntity<TaskDto> getById(@PathVariable @NonNull UUID id);

    @Override
    @GetMapping("/all")
    @Operation(description = "Getting all tasks")
    ResponseEntity<List<TaskDto>> getAll(Pageable page);

    @Override
    @PostMapping(value = "/create")
    @Operation(description = "Creating task")
    ResponseEntity<TaskDto> create(@RequestBody @NonNull TaskDto dto);

    @Override
    @PutMapping(value = "/update")
    @Operation(description = "Updating task")
    ResponseEntity<TaskDto> update(@RequestBody @NonNull TaskDto dto);

    @Override
    @DeleteMapping(value = "/{id}")
    @Operation(description = "Deleting task")
    ResponseEntity<UUID> deleteById(@PathVariable @NonNull UUID id);

    @GetMapping("/by/author")
    @Operation(description = "Getting tasks by author")
    ResponseEntity<List<TaskDto>> getTasksByAuthor(Pageable page, @RequestParam @NonNull UUID authorId);

    @GetMapping("/by/executor")
    @Operation(description = "Getting tasks by executor")
    ResponseEntity<List<TaskDto>> getTasksByExecutor(Pageable page, @RequestParam @NonNull UUID executorId);
}
