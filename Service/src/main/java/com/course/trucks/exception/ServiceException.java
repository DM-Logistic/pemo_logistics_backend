package com.course.trucks.exception;

public class ServiceException extends Exception {

    private final String message;

    public ServiceException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
