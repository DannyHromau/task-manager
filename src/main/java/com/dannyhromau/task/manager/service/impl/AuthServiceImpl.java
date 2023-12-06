package com.dannyhromau.task.manager.service.impl;

import com.dannyhromau.task.manager.core.config.ErrorMessages;
import com.dannyhromau.task.manager.core.security.config.AuthValidationConfig;
import com.dannyhromau.task.manager.core.security.config.SecurityConfig;
import com.dannyhromau.task.manager.core.security.jwt.JwToken;
import com.dannyhromau.task.manager.core.security.jwt.JwtTokenProvider;
import com.dannyhromau.task.manager.exception.InvalidDataException;
import com.dannyhromau.task.manager.exception.UnAuthorizedException;
import com.dannyhromau.task.manager.model.User;
import com.dannyhromau.task.manager.service.AuthService;
import com.dannyhromau.task.manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final AuthValidationConfig avConfig;
    private final JwtTokenProvider tokenProvider;
    private final SecurityConfig securityConfig;
    private final BCryptPasswordEncoder encoder;
    private static final String WRONG_EMAIL_FORMAT_MESSAGE = ErrorMessages.WRONG_EMAIL_MESSAGE.label;
    private static final String WRONG_PASSWORD_FORMAT_MESSAGE = ErrorMessages.WRONG_PASSWORD_MESSAGE.label;
    private static final String WRONG_AUTHENTICATION_MESSAGE = ErrorMessages.WRONG_AUTHENTICATION_MESSAGE.label;
    private static final String WRONG_REFRESH_TOKEN_MESSAGE = ErrorMessages.WRONG_TOKEN_MESSAGE.label;
    private String emailRegex = "^(.+)@(\\S+)$";
    private String passwordPatternRegex = "\\S{8,20}";


    @Override
    public boolean register(User user) {
        user = checkValidData(user);
        user.setPassword(encoder.encode(user.getPassword()));
        return userService.addEntity(user) != null;
    }

    @Override
    public JwToken authorize(User user) {
        User authUser;
        try {
            authUser = userService.getEntityByEmail(user.getEmail());
        } catch (Exception e) {
            authUser = null;
        }
        if (authUser != null && encoder.matches(user.getPassword(), authUser.getPassword())) {
            String accessToken = tokenProvider.createToken(user.getId(), user.getEmail());
            String refreshToken = tokenProvider.refreshToken(user.getId());
            return new JwToken(accessToken, refreshToken);
        } else {
            throw new UnAuthorizedException(WRONG_AUTHENTICATION_MESSAGE);
        }
    }

    @Override
    public JwToken refresh(JwToken token) {
        try {
            UUID id = UUID
                    .fromString((String) securityConfig
                            .jwtDecoder()
                            .decode(token.getRefreshToken())
                            .getClaims()
                            .get("id"));
            User user = userService.getEntityById(id);
            return new JwToken(tokenProvider.createToken(user.getId(), user.getEmail()),
                    tokenProvider.refreshToken(user.getId()));
        } catch (Exception e) {
            throw new UnAuthorizedException(WRONG_REFRESH_TOKEN_MESSAGE);
        }
    }

    @Override
    public User checkValidData(User user) {
        emailRegex = avConfig.getEmailPattern() == null ? emailRegex : avConfig.getEmailPattern();
        passwordPatternRegex = avConfig.getPasswordPattern() == null ?
                passwordPatternRegex : avConfig.getPasswordPattern();
        if (!user.getEmail().matches(emailRegex)) {
            throw new InvalidDataException(WRONG_EMAIL_FORMAT_MESSAGE);
        }
        if (!user.getPassword().matches(passwordPatternRegex)) {
            throw new InvalidDataException(WRONG_PASSWORD_FORMAT_MESSAGE);
        }
        return user;
    }
}
