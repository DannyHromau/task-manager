package com.dannyhromau.task.test;

import com.dannyhromau.task.manager.Application;
import com.dannyhromau.task.manager.exception.EntityNotfoundException;
import com.dannyhromau.task.manager.exception.InvalidDataException;
import com.dannyhromau.task.manager.model.Comment;
import com.dannyhromau.task.manager.model.Task;
import com.dannyhromau.task.manager.model.User;
import com.dannyhromau.task.manager.repository.CommentRepository;
import com.dannyhromau.task.manager.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.Assertions;
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

@DisplayName("Testing of comment_service")
@SpringBootTest(classes = Application.class)
public class CommentServiceImplTest {

    @InjectMocks
    private CommentServiceImpl commentService;
    @Mock
    private CommentRepository commentRepository;
    private Comment comment;

    @BeforeEach
    void setup() {
        comment = new Comment();
        User user = new User();
        user.setId(UUID.fromString("d164f0fc-93f8-11ee-b9d1-0242ac120002"));
        user.setPassword("Qasdfc123_@");
        user.setEmail("UserMyUser@mail.com");
        Task task = new Task();
        task.setHeader("Testing of task_service");
        task.setDescription("task_service");
        task.setCreatedOn(ZonedDateTime.now());
        task.setPriority(Task.Priority.LOW);
        task.setStatus(Task.Status.AWAITING);
        task.setAuthor(user);
        task.setExecutor(user);
        task.setAuthor_id(user.getId());
        task.setExecutor_id(user.getId());
        task.setId(UUID.fromString("d164f0fc-93f8-11ee-b9d1-0242ac120002"));
        comment.setTask(task);
        comment.setValue("value");
        UUID commentId = UUID.randomUUID();
        comment.setId(commentId);
        comment.setAuthor(user);
        comment.setTaskId(task.getId());
        comment.setAuthorId(user.getId());
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);
        task.setCommentList(commentList);
    }

    @Test
    @DisplayName("add comment when valid id")
    void addCommentWhenValidId() {
        when(commentRepository.findById(comment.getId())).thenReturn(null);
        when(commentRepository.save(comment)).thenReturn(comment);
        Comment expectedComment = commentService.addEntity(comment);
        Assertions.assertEquals(expectedComment, comment);
    }

    @Test
    @DisplayName("Get comment when id doesn't exist")
    void getCommentWhenInvalidId() {
        when(commentRepository.save(comment)).thenReturn(null);
        Exception exception = assertThrows(EntityNotfoundException.class, () ->
                commentService.getEntityById(comment.getId()));
        String expectedMessage = String.format("Entity with id : %s not exists", comment.getId());
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("add comment when it is empty")
    void addCommentWhenEmpty() {
        comment.setValue(" ");
        Exception exception = assertThrows(InvalidDataException.class, () ->
                commentService.addEntity(comment));
        String expectedMessage = "Incorrect or duplicated data";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("delete comment when doesn't exist")
    void deleteUserWhenNotExists() {
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotfoundException.class, () ->
                commentService.deleteEntity(comment.getId()));
        String expectedMessage = String.format("Entity with id : %s not exists", comment.getId());
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
