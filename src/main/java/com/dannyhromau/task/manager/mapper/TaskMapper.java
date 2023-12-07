package com.dannyhromau.task.manager.mapper;

import com.dannyhromau.task.manager.api.dto.CommentDto;
import com.dannyhromau.task.manager.api.dto.TaskDto;
import com.dannyhromau.task.manager.model.Comment;
import com.dannyhromau.task.manager.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CommentMapper.class})
public interface TaskMapper {
    Task mapToTask(TaskDto taskDto);
    TaskDto mapToTaskDto(Task task);
    void updateTaskFromDto(TaskDto taskDto, @MappingTarget Task task);
    List<TaskDto> mapToListTaskDto(List<Task> taskList);
}
