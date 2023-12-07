package com.dannyhromau.task.manager.facade.impl;

import com.dannyhromau.task.manager.api.dto.UserDto;
import com.dannyhromau.task.manager.facade.UserFacade;
import com.dannyhromau.task.manager.model.User;
import com.dannyhromau.task.manager.service.UserService;
import com.dannyhromau.task.manager.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getDtos(Pageable pageable) {
        return userMapper.mapToListUserDto(userService.getEntities(pageable));
    }

    @Override
    public UserDto getDtoByID(UUID id) {
        return userMapper.mapToUserDto(userService.getEntityById(id));
    }

    @Override
    public UserDto addDto(UserDto dto) {
        User user = userService.addEntity(userMapper.mapToUser(dto));
        return userMapper.mapToUserDto(user);
    }

    @Override
    public UUID deleteDtoById(UUID id) {
        return userService.deleteEntity(id);
    }

    @Override
    public UserDto updateDto(UserDto dto) {
        User user = userService.getEntityById(dto.getId());
        userMapper.updateUserFromDto(dto, user);
        return userMapper.mapToUserDto(user);
    }
}
