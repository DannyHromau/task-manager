package com.dannyhromau.task.manager.facade;

import com.dannyhromau.task.manager.api.dto.UserDto;
import com.dannyhromau.task.manager.core.base.BaseFacade;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface UserFacade extends BaseFacade<UserDto> {
    @Override
    List<UserDto> getDtos(Pageable pageable);

    @Override
    UserDto getDtoByID(UUID id);

    @Override
    UserDto addDto(UserDto dto);

    @Override
    UUID deleteDtoById(UUID id);

    @Override
    UserDto updateDto(UserDto dto);
}
