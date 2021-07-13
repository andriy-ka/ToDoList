package andriy.todolist.controller;

import andriy.todolist.exeption.InvalidInputDataException;
import andriy.todolist.exeption.UserAlreadyExistException;
import andriy.todolist.model.Error;
import andriy.todolist.model.enums.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.OK)
    public String handleUserAlreadyExistException(final UserAlreadyExistException ex, Model model) {
        model.addAttribute("error", new Error(ex.getMessage(), ErrorType.REGISTRATION_ERROR_TYPE, LocalDateTime.now()));
        model.addAttribute("action", "register");
        return "errorPage";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoHandlerFoundException(final NoHandlerFoundException ex, Model model) {
        model.addAttribute("error", new Error(ex.getMessage(), ErrorType.NOT_FOUND_ERROR_TYPE, LocalDateTime.now()));
        model.addAttribute("action", "home");
        return "errorPage";
    }

    @ExceptionHandler(InvalidInputDataException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleInvalidInputDataException(final InvalidInputDataException ex, Model model) {
        model.addAttribute("error", new Error(ex.getMessage(), ErrorType.INVALID_ERROR_TYPE, LocalDateTime.now()));
        model.addAttribute("action", "register");
        return "errorPage";
    }

}
