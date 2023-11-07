package com.example.bankapp.controller.exception;

import com.example.bankapp.dto.ErrorData;
import com.example.bankapp.exceptions.NotEnoughMoneyException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(RuntimeException.class) // обязательно указать тип ошибки
    public ResponseEntity<ErrorData> handleRuntimeException(RuntimeException exception){
    ErrorData errorData = new ErrorData(HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now(),
            exception.getMessage(), Arrays.toString(exception.getStackTrace()));
    return new ResponseEntity<>(errorData, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorData> handleEntityNotFoundException(EntityNotFoundException entityNotFoundException){
        ErrorData errorData = new ErrorData(HttpStatus.NO_CONTENT, LocalDateTime.now(),
                entityNotFoundException.getMessage(), Arrays.toString(entityNotFoundException.getStackTrace()));
        return new ResponseEntity<>(errorData, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<ErrorData> handleNotEnoughMoneyException(NotEnoughMoneyException notEnoughMoneyException){
        ErrorData errorData = new ErrorData(HttpStatus.NOT_ACCEPTABLE, LocalDateTime.now(),
                notEnoughMoneyException.getMessage(), Arrays.toString(notEnoughMoneyException.getStackTrace()));
        return new ResponseEntity<>(errorData, HttpStatus.NOT_ACCEPTABLE);
    }
}
