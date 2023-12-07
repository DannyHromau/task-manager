package com.dannyhromau.task.manager.mapper;

import com.dannyhromau.task.manager.api.dto.TokenDto;
import com.dannyhromau.task.manager.core.security.jwt.JwToken;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenMapper {
    JwToken mapToToken(TokenDto tokenDto);

    TokenDto mapToTokenDto(JwToken token);
}
