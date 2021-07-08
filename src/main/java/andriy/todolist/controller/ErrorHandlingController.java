package andriy.todolist.controller;

import andriy.todolist.exeption.UserAlreadyExistException;
import andriy.todolist.model.enums.ErrorType;
import andriy.todolist.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleUserAlreadyExistException(UserAlreadyExistException ex){
        return new Error(ex.getMessage(), ErrorType.VALIDATION_ERROR_TYPE, LocalDateTime.now());
    }
}
