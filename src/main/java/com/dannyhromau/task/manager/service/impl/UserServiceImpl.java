package com.dannyhromau.task.manager.service.impl;

import com.dannyhromau.task.manager.core.config.ErrorMessages;
import com.dannyhromau.task.manager.exception.EntityNotfoundException;
import com.dannyhromau.task.manager.exception.InvalidDataException;
import com.dannyhromau.task.manager.model.User;
import com.dannyhromau.task.manager.repository.UserRepository;
import com.dannyhromau.task.manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private static final String ENTITY_NOT_FOUND_MESSAGE = ErrorMessages.ENTITY_NOT_FOUND_MESSAGE.label;
    private static final String NULLABLE_ID_MESSAGE = ErrorMessages.NULLABLE_ID_MESSAGE.label;
    private static final String DUPLICATE_USER_MESSAGE = ErrorMessages.DUPLICATE_USER_MESSAGE.label;

    @Override
    public List<User> getEntities(Pageable pageable) {
        return userRepository.findAll(pageable).toList();
    }

    @Override
    public User getEntityById(UUID id) {
        return checkValidData(id);
    }

    @Override
    public User addEntity(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new InvalidDataException(DUPLICATE_USER_MESSAGE);
        } else {
            return userRepository.save(user);
        }
    }

    @Override
    public UUID deleteEntity(UUID id) {
        userRepository.deleteById(checkValidData(id).getId());
        return id;
    }

    @Override
    public User updateEntity(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getEntityByEmail(String email) throws IllegalArgumentException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException(ENTITY_NOT_FOUND_MESSAGE);
        }
        return null;
    }

    private User checkValidData(UUID id) {
        if (id == null) {
            throw new InvalidDataException(NULLABLE_ID_MESSAGE);
        }
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new EntityNotfoundException(String.format(ENTITY_NOT_FOUND_MESSAGE, id));
        } else {
            return userOpt.get();
        }
    }
}
