package com.dannyhromau.task.manager.api.dto;

import com.dannyhromau.task.manager.core.base.BaseDto;
import com.dannyhromau.task.manager.model.Task;
import com.dannyhromau.task.manager.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CommentDto extends BaseDto {
    private String value;
    private UUID authorId;
    private UUID taskId;
    private User author;
    private Task task;
}
