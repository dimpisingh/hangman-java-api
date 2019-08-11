package com.dell.hangman.exception.advice;

import com.dell.hangman.dto.ExceptionInfo;
import com.dell.hangman.controller.GameController;
import com.dell.hangman.exception.BadRequestException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = GameController.class)
public class GameControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BadRequestException.class)
    public ExceptionInfo BadRequestExceptionHandler(BadRequestException exception) {
        return ExceptionInfo.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionInfo ExceptionHandler(Exception exception) {
        return ExceptionInfo.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
    }

}
