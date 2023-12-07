package com.dannyhromau.task.manager.api.dto;

import com.dannyhromau.task.manager.core.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto extends BaseDto {
    private String email;
    @JsonIgnore
    private List<TaskDto> issuedTaskList;
    @JsonIgnore
    private List<TaskDto> receivedTaskList;
    @JsonIgnore
    private List<CommentDto> commentList;
}
