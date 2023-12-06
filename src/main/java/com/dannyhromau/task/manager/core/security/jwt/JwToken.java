package com.dannyhromau.task.manager.core.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwToken {
    private String accessToken;
    private String refreshToken;
}
