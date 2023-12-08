package com.dannyhromau.task.manager.controller.impl;

import com.dannyhromau.task.manager.api.dto.UserDto;
import com.dannyhromau.task.manager.controller.UserController;
import com.dannyhromau.task.manager.facade.UserFacade;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserFacade userFacade;

    @Override
    public ResponseEntity<UserDto> getById(@NonNull UUID id) {
        log.info("call getById in user with id: {}", id);
        return ResponseEntity.ok(userFacade.getDtoByID(id));
    }

    @Override
    public ResponseEntity<List<UserDto>> getAll(Pageable page) {
        log.info("call get all users");
        return ResponseEntity.ok(userFacade.getDtos(page));
    }

    @Override
    public ResponseEntity<UserDto> create(@RequestBody @NonNull UserDto dto) {
        log.info("call create in user: {}, user", dto);
        return ResponseEntity.ok(userFacade.addDto(dto));
    }

    @Override
    public ResponseEntity<UserDto> update(@RequestBody @NonNull UserDto dto) {
        log.info("call update in user: {}, user", dto);
        return ResponseEntity.ok(userFacade.updateDto(dto));
    }

    @Override
    public ResponseEntity<UUID> deleteById(@NonNull UUID id) {
        log.info("call delete in user with id: {}", id);
        return ResponseEntity.ok(userFacade.deleteDtoById(id));
    }
}
