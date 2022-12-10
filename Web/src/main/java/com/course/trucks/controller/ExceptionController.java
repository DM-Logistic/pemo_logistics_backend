package com.course.trucks.controller;

import com.course.trucks.exception.InvalidPasswordException;
import com.course.trucks.exception.JwtAuthenticationException;
import com.course.trucks.exception.NotVerifiedException;
import com.course.trucks.exception.ServiceException;
import com.course.trucks.message.AnswerMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionController {

    private static final Logger LOGGER = Logger.getLogger(ExceptionController.class);

    private final AnswerMessage answerMessage;

    @Autowired
    public ExceptionController(AnswerMessage answerMessage) {
        this.answerMessage = answerMessage;
    }

    @ExceptionHandler(value = JwtAuthenticationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public AnswerMessage handleJwtAuthenticationException(JwtAuthenticationException e) {
        LOGGER.error("Handle JwtAuthenticationException");
        setAnswerMessage(e.getMessage(), HttpStatus.FORBIDDEN.toString());
        return answerMessage;
    }

    @ExceptionHandler(value = InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public AnswerMessage handleInvalidPasswordException(InvalidPasswordException e) {
        LOGGER.error("Handle InvalidPasswordException");
        setAnswerMessage(e.getMessage(), HttpStatus.FORBIDDEN.toString());
        return answerMessage;
    }

    @ExceptionHandler(value = NotVerifiedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public AnswerMessage handleNotVerifiedException(NotVerifiedException e) {
        LOGGER.error("Handle NotVerifiedException");
        setAnswerMessage(e.getMessage(), HttpStatus.FORBIDDEN.toString());
        return answerMessage;
    }

    @ExceptionHandler(value = ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AnswerMessage handleServiceException(ServiceException e) {
        LOGGER.error("Handle ServiceException");
        setAnswerMessage(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return answerMessage;
    }

    @ExceptionHandler(value = Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AnswerMessage handleThrowable(Throwable e) {
        LOGGER.error("Handle Throwable");
        e.printStackTrace();
        setAnswerMessage(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return answerMessage;
    }

    private void setAnswerMessage(String message, String status) {
        answerMessage.setMessage(message);
        answerMessage.setStatus(status);
    }
}
