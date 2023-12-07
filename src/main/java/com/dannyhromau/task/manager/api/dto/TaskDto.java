package com.dannyhromau.task.manager.api.dto;

import com.dannyhromau.task.manager.core.base.BaseDto;
import com.dannyhromau.task.manager.model.Comment;
import com.dannyhromau.task.manager.model.Task;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class TaskDto extends BaseDto {
    private String header;
    private String description;
    private Task.Status status;
    private Task.Priority priority;
    private UUID author_id;
    private UUID executor_id;
    private ZonedDateTime createdOn;
    @JsonIgnore
    private List<Comment> commentList;
}
