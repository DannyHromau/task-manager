package com.dannyhromau.task.test;

import com.dannyhromau.task.manager.Application;
import com.dannyhromau.task.manager.core.security.config.AuthValidationConfig;
import com.dannyhromau.task.manager.core.security.config.SecurityConfig;
import com.dannyhromau.task.manager.core.security.jwt.JwToken;
import com.dannyhromau.task.manager.core.security.jwt.JwtConfig;
import com.dannyhromau.task.manager.core.security.jwt.JwtTokenProvider;
import com.dannyhromau.task.manager.exception.EntityNotfoundException;
import com.dannyhromau.task.manager.exception.InvalidDataException;
import com.dannyhromau.task.manager.exception.UnAuthorizedException;
import com.dannyhromau.task.manager.model.User;
import com.dannyhromau.task.manager.service.impl.AuthServiceImpl;
import com.dannyhromau.task.manager.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@DisplayName("Testing of auth_service")
@EnableConfigurationProperties(JwtConfig.class)
@TestPropertySource("classpath:application-test.yml")
@SpringBootTest(classes = Application.class)
public class AuthServiceImplTest {

    private AuthServiceImpl authService;
    @Mock
    private UserServiceImpl userService;
    @Mock
    private AuthValidationConfig avConfig;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private JwtTokenProvider provider;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    private JwtDecoder decoder;

    @BeforeEach
    public void setup() {
       authService = new AuthServiceImpl(userService, avConfig, provider, securityConfig, encoder);
    }

    @Test
    @DisplayName("register user when wrong password format and correct email")
    void registerUserWhenInvalidPassword() {
        User user = new User();
        user.setId(UUID.fromString("d164f0fc-93f8-11ee-b9d1-0242ac120002"));
        user.setPassword("Qa");
        user.setEmail("usermyser@mail.com");
        when(avConfig.getEmailPattern()).thenReturn(null);
        when(avConfig.getPasswordPattern()).thenReturn(null);
        Exception exception = assertThrows(InvalidDataException.class, () ->
                authService.register(user));
        String expectedMessage = "Wrong password format";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("register user when wrong email format and correct password")
    void registerUserWhenInvalidEmail() {
        User user = new User();
        user.setId(UUID.fromString("d164f0fc-93f8-11ee-b9d1-0242ac120002"));
        user.setPassword("aaaaaaaa");
        user.setEmail("usermyser#mail.com");
        when(avConfig.getEmailPattern()).thenReturn(null);
        when(avConfig.getPasswordPattern()).thenReturn(null);
        Exception exception = assertThrows(InvalidDataException.class, () ->
                authService.register(user));
        String expectedMessage = "Wrong email format";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("authorize user with correct credentials")
    void authUserWhenValidCredentials() {
        User authUser = new User();
        authUser.setId(UUID.fromString("d164f0fc-93f8-11ee-b9d1-0242ac120002"));
        authUser.setPassword(encoder.encode("aaaaaaaa"));
        authUser.setEmail("usermyser@mail.com");
        User user = new User();
        user.setId(UUID.fromString("d164f0fc-93f8-11ee-b9d1-0242ac120002"));
        user.setPassword("aaaaaaaa");
        user.setEmail("usermyser@mail.com");
        when(userService.getEntityByEmail(authUser.getEmail())).thenReturn(authUser);
        JwToken actualToken = authService.authorize(user);
        Jwt jwt = decoder.decode(actualToken.getAccessToken());
        String actualEmail = (String) jwt.getClaims().get("email");
        assertTrue(actualEmail.contains(user.getEmail()));
    }

    @Test
    @DisplayName("authorize user with incorrect password")
    void authUserWhenInValidPassword() {
        User authUser = new User();
        authUser.setId(UUID.fromString("d164f0fc-93f8-11ee-b9d1-0242ac120002"));
        authUser.setPassword(encoder.encode("aaaaaaaa"));
        authUser.setEmail("usermyser@mail.com");
        User user = new User();
        user.setId(UUID.fromString("d164f0fc-93f8-11ee-b9d1-0242ac120002"));
        user.setPassword("bbbbbb");
        user.setEmail("usermyser@mail.com");
        when(userService.getEntityByEmail(authUser.getEmail())).thenReturn(authUser);
        Exception exception = assertThrows(UnAuthorizedException.class, () ->
                authService.authorize(user));
        String expectedMessage = "Cannot authorize : check input data or unblock the user";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("authorize user with incorrect email")
    void authUserWhenInValidEmail() {
        User authUser = new User();
        authUser.setId(UUID.fromString("d164f0fc-93f8-11ee-b9d1-0242ac120002"));
        authUser.setPassword(encoder.encode("aaaaaaaa"));
        authUser.setEmail("usermyser@mail.com");
        User user = new User();
        user.setId(UUID.fromString("d164f0fc-93f8-11ee-b9d1-0242ac120002"));
        user.setPassword("aaaaaaaa");
        user.setEmail("usermyser@mail.ru");
        when(userService.getEntityByEmail(authUser.getEmail())).thenReturn(authUser);
        Exception exception = assertThrows(UnAuthorizedException.class, () ->
                authService.authorize(user));
        String expectedMessage = "Cannot authorize : check input data or unblock the user";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    @DisplayName("refresh with valid token")
    void refreshWhenValidToken() {
        User authUser = new User();
        authUser.setId(UUID.fromString("d164f0fc-93f8-11ee-b9d1-0242ac120002"));
        authUser.setPassword(encoder.encode("aaaaaaaa"));
        authUser.setEmail("usermyser@mail.com");
        String accessToken = provider.createToken(authUser.getId(), authUser.getEmail());
        String refreshToken = provider.refreshToken(authUser.getId());
        JwToken token = new JwToken(accessToken, refreshToken);
        when(userService.getEntityById(authUser.getId())).thenReturn(authUser);
        JwToken actualToken = authService.refresh(token);
        UUID actualId = UUID.fromString((String) decoder.decode(actualToken.getRefreshToken()).getClaims().get("id"));
        assertTrue(actualId.equals(authUser.getId()));
    }
    @Test
    @DisplayName("refresh with invalid token")
    void refreshWhenInValidToken() {
        User user = new User();
        user.setId(UUID.fromString("d164f0fc-93f8-11ee-b9d1-0242ac120002"));
        user.setPassword(encoder.encode("aaaaaaaa"));
        user.setEmail("usermyser@mail.com");
        String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6ImQxNjRmMGZjLTkzZjgtMTFlZS1iOWQxLTAyNDJhYzEyMDAwMiIsImV4cCI6MTcwMTg1OTM4NCwiZW1haWwiOiJ1c2VybXlzZXJAbWFpbC5jb20ifQ.KQdCjcNocfGrmB0OkCAzZbmkuZIw4O894W-wUu3IjrA";
        String refreshToken = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MDE4NjkyODQsImlkIjoiZDE2NGYwZmMtOTNmOC0xMWVlLWI5ZDEtMDI0MmFjMTIwMDAyIn0.YzQd-Orr-ysZhxWWm5EbXztk-F1msnZ0RJXW_NgiOc8";
        JwToken token = new JwToken(accessToken, refreshToken);
        when(userService.getEntityById(user.getId())).thenReturn(null);
        Exception exception = assertThrows(UnAuthorizedException.class, () ->
                authService.refresh(token));
        String expectedMessage = "Wrong token";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


}
