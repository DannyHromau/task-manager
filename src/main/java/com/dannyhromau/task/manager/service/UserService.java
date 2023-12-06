package com.dannyhromau.task.manager.service;

import com.dannyhromau.task.manager.core.base.BaseService;
import com.dannyhromau.task.manager.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserService extends BaseService<User> {
    @Override
    List<User> getEntities(Pageable pageable);

    @Override
    User getEntityById(UUID id);

    @Override
    User addEntity(User entity);

    @Override
    UUID deleteEntity(UUID id);

    @Override
    User updateEntity(User entity);

    User getEntityByEmail(String email) throws Exception;
}
