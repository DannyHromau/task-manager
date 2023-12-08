package com.dannyhromau.task.manager.mapper;

import com.dannyhromau.task.manager.api.dto.UserDto;
import com.dannyhromau.task.manager.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TaskMapper.class, CommentMapper.class})
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User mapToUser(UserDto userDto);

    UserDto mapToUserDto(User user);


    void updateUserFromDto(UserDto userDto, @MappingTarget User user);

    List<UserDto> mapToListUserDto(List<User> users);
}