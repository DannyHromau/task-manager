package com.dannyhromau.task.manager.facade;

import com.dannyhromau.task.manager.api.dto.TaskDto;
import com.dannyhromau.task.manager.core.base.BaseFacade;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface TaskFacade extends BaseFacade<TaskDto> {
    @Override
    List<TaskDto> getDtos(Pageable pageable);

    @Override
    TaskDto getDtoByID(UUID id);

    @Override
    TaskDto addDto(TaskDto dto);

    @Override
    UUID deleteDtoById(UUID id);

    @Override
    TaskDto updateDto(TaskDto dto);
}
