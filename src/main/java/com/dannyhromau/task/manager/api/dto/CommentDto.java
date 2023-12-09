package com.dannyhromau.task.manager.api.dto;

import com.dannyhromau.task.manager.core.base.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CommentDto extends BaseDto {
    private String value;
    private UUID authorId;
    private UUID taskId;
}
