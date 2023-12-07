package com.dannyhromau.task.manager.facade.impl;

import com.dannyhromau.task.manager.api.dto.AuthDto;
import com.dannyhromau.task.manager.api.dto.TokenDto;
import com.dannyhromau.task.manager.core.security.jwt.JwToken;
import com.dannyhromau.task.manager.facade.AuthFacade;
import com.dannyhromau.task.manager.model.User;
import com.dannyhromau.task.manager.service.AuthService;
import com.dannyhromau.task.manager.mapper.TokenMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthFacadeImpl implements AuthFacade {
    private final AuthService authService;
    private final TokenMapper tokenMapper;

    @Override
    public TokenDto authorize(AuthDto authDto) {
        User user = new User(authDto.getEmail(), authDto.getPassword());
        JwToken token = authService.authorize(user);
        return tokenMapper.mapToTokenDto(token);
    }

    @Override
    public boolean register(AuthDto registerDto) {
        User user = new User(registerDto.getEmail(), registerDto.getPassword());
        return authService.register(user);
    }

    @Override
    public TokenDto refresh(TokenDto refreshToken) {
        JwToken token = authService.refresh(tokenMapper.mapToToken(refreshToken));
        return tokenMapper.mapToTokenDto(token);
    }
}
