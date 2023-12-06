package com.dannyhromau.task.test;

import com.dannyhromau.task.manager.Application;
import com.dannyhromau.task.manager.exception.EntityNotfoundException;
import com.dannyhromau.task.manager.exception.InvalidDataException;
import com.dannyhromau.task.manager.model.User;
import com.dannyhromau.task.manager.repository.UserRepository;
import com.dannyhromau.task.manager.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@DisplayName("Testing of userservice")
@SpringBootTest(classes = Application.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;


    @Test
    @DisplayName("add user when valid id")
    void getUserWhenValidId() {
        User user = new User();
        user.setId(UUID.fromString("d164f0fc-93f8-11ee-b9d1-0242ac120002"));
        user.setPassword("Qasdfc123_@");
        user.setEmail("UserMyUser@mail.com");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
        when(userRepository.save(user)).thenReturn(user);
        User expectedUser = userService.addEntity(user);
        Assertions.assertEquals(expectedUser, user);
    }
    @Test
    @DisplayName("Get user when id doesn't exist")
    void getUserWhenInvalidId() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(null);
        user.setId(UUID.fromString("d164f0fc-93f8-11ee-b9d1-0242ac120002"));
        user.setPassword("Qasdfc123_@");
        user.setEmail("UserMyUser@mail.com");
        Exception exception = assertThrows(EntityNotfoundException.class, () ->
                userService.getEntityById(user.getId()));
        String expectedMessage = String.format("Entity with id : %s not exists", user.getId());
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("add user when already exists")
    void addUserWhenExists() {
        User user = new User();
        user.setId(UUID.fromString("d164f0fc-93f8-11ee-b9d1-0242ac120002"));
        user.setPassword("Qasdfc123_@");
        user.setEmail("UserMyUser@mail.com");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        Exception exception = assertThrows(InvalidDataException.class, () ->
                userService.addEntity(user));
        String expectedMessage = "The given user already exists";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("delete user when doesn't exist")
    void deleteUserWhenNotExists() {
        User user = new User();
        user.setId(UUID.fromString("d164f0fc-93f8-11ee-b9d1-0242ac120002"));
        user.setPassword("Qasdfc123_@");
        user.setEmail("UserMyUser@mail.com");
        Exception exception = assertThrows(EntityNotfoundException.class, () ->
                userService.deleteEntity(user.getId()));
        String expectedMessage =  String.format("Entity with id : %s not exists", user.getId());
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
