package com.dannyhromau.task.manager.facade;

import com.dannyhromau.task.manager.api.dto.AuthDto;
import com.dannyhromau.task.manager.api.dto.TokenDto;

public interface AuthFacade {
    TokenDto authorize(AuthDto authDto);

    boolean register(AuthDto registerDto);

    TokenDto refresh(TokenDto refreshToken);
}
