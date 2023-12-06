package com.dannyhromau.task.manager.core.advice;

import com.dannyhromau.task.manager.api.dto.ErrorMessageDto;
import com.dannyhromau.task.manager.core.config.ErrorMessages;
import com.dannyhromau.task.manager.exception.EntityNotfoundException;
import com.dannyhromau.task.manager.exception.InvalidDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler({EntityNotfoundException.class})
    protected ResponseEntity<ErrorMessageDto> notFoundHandler(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessageDto(e.getMessage()));
    }

    @ExceptionHandler({InvalidDataException.class,
            DataIntegrityViolationException.class, IllegalArgumentException.class})
    protected ResponseEntity<ErrorMessageDto> invalidDataHandler(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessageDto(ErrorMessages.INCORRECT_DATA_MESSAGE.label));
    }

//    @ExceptionHandler({Exception.class})
//    protected ResponseEntity<ErrorMessageDto> conflictHandler(Exception e) {
//        log.error(e.getMessage());
//        return ResponseEntity.status(HttpStatus.CONFLICT).build();
//    }

    @ExceptionHandler({SQLException.class})
    protected ResponseEntity<ErrorMessageDto> dbProblemsHandler(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ErrorMessageDto> unauthorizedHandler(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
