package com.dannyhromau.task.manager.service;

import com.dannyhromau.task.manager.core.base.BaseService;
import com.dannyhromau.task.manager.core.security.jwt.JwToken;
import com.dannyhromau.task.manager.model.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    boolean register(User user);

    JwToken authorize(User user);

    JwToken refresh(JwToken token);

    User checkValidData(User user);
}