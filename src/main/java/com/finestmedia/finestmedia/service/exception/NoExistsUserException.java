package com.finestmedia.finestmedia.service.exception;

public class NoExistsUserException extends RuntimeException {
    public NoExistsUserException(Long userId) {
        super(String.format("User with id: %s doesn't exist", userId));
    }
}
