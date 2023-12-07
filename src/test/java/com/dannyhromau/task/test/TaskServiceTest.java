package com.dannyhromau.task.test;

import com.dannyhromau.task.manager.Application;
import com.dannyhromau.task.manager.core.util.TokenUtil;
import com.dannyhromau.task.manager.core.util.UserDetails;
import com.dannyhromau.task.manager.exception.ForbiddenException;
import com.dannyhromau.task.manager.exception.InvalidDataException;
import com.dannyhromau.task.manager.model.Comment;
import com.dannyhromau.task.manager.model.Task;
import com.dannyhromau.task.manager.model.User;
import com.dannyhromau.task.manager.repository.TaskRepository;
import com.dannyhromau.task.manager.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@DisplayName("Testing of task_service")
@SpringBootTest(classes = Application.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskServiceImpl taskService;
    @Mock
    private TaskRepository taskRepository;
    private Task task;
    @Mock
    private TokenUtil tokenUtil;

    @BeforeEach
    void setup(){
        User author = new User();
        User executor = new User();
        Comment comment = new Comment();
        author.setId(UUID.fromString("a2fa0a18-94cf-11ee-b9d1-0242ac120002"));
        executor.setId(UUID.fromString("b62a60f6-94cf-11ee-b9d1-0242ac120002"));
        task = new Task();
        task.setHeader("Testing of task_service");
        task.setDescription("task_service");
        task.setCreatedOn(ZonedDateTime.now());
        task.setPriority(Task.Priority.LOW);
        task.setStatus(Task.Status.AWAITING);
        task.setAuthor(author);
        task.setExecutor(executor);
        task.setAuthor_id(author.getId());
        task.setExecutor_id(executor.getId());
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);
        task.setCommentList(commentList);
    }

    @Test
    @DisplayName("add task when already exists")
    void addTaskWhenExists() {
        task.setId(UUID.fromString("d164f0fc-93f8-11ee-b9d1-0242ac120002"));
        Exception exception = assertThrows(InvalidDataException.class, () ->
                taskService.addEntity(task));
        String expectedMessage = "Incorrect or duplicated data";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    @DisplayName("delete task when there are no authorities")
    void deleteTaskWhenNoAuthorities() {
        UserDetails ud = new UserDetails();
        ud.setId(UUID.fromString("b62a60f6-94cf-11ee-b9d1-0242ac120002"));
        when(tokenUtil.getUserDetails()).thenReturn(ud);
        when(taskRepository.findById(UUID.fromString("d164f0fc-93f8-11ee-b9d1-0242ac120002"))).thenReturn(Optional.of(task));
        Exception exception = assertThrows(ForbiddenException.class, () ->
                taskService.deleteEntity(UUID.fromString("d164f0fc-93f8-11ee-b9d1-0242ac120002")));
        String expectedMessage = "Permission denied : no such authorities";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("update task when there are no authorities")
    void updateTaskWhenNoAuthorities() {
        UserDetails ud = new UserDetails();
        String wrongUuid = "c169c468-94d7-11ee-b9d1-0242ac120002";
        ud.setId(UUID.fromString(wrongUuid));
        when(tokenUtil.getUserDetails()).thenReturn(ud);
        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));
        Exception exception = assertThrows(ForbiddenException.class, () ->
                taskService.updateEntity(task));
        String expectedMessage = "Permission denied : no such authorities";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}
