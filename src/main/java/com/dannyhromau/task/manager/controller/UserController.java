package com.dannyhromau.task.manager.controller;

import com.dannyhromau.task.manager.api.dto.UserDto;
import com.dannyhromau.task.manager.core.base.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "Comment service", description = "Comment controller")
@ApiResponse(responseCode = "200", description = "Successful operation")
@ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
@ApiResponse(responseCode = "404", description = "Not found")
@ApiResponse(responseCode = "401", description = "Unauthorized")
@ApiResponse(responseCode = "503", description = "Service unavailable")
public interface UserController extends BaseController<UserDto> {
    @Override
    @GetMapping("/{id}")
    @Operation(description = "Getting user by id")
    ResponseEntity<UserDto> getById(@PathVariable @NonNull UUID id);

    @Override
    @GetMapping("/all")
    @Operation(description = "Getting all users")
    ResponseEntity<List<UserDto>> getAll(Pageable page);

    @Override
    @PostMapping(value = "/create")
    @Operation(description = "Creating user")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    ResponseEntity<UserDto> create(@RequestBody @NonNull UserDto dto);

    @Override
    @PutMapping(value = "/update")
    @Operation(description = "Updating user")
    ResponseEntity<UserDto> update(@RequestBody @NonNull UserDto dto);

    @Override
    @DeleteMapping(value = "/{id}")
    @Operation(description = "Deleting user")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    ResponseEntity<UUID> deleteById(@PathVariable @NonNull UUID id);
}
