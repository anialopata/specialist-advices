package com.anialopata.registration.exception;

/**
 * Created by Ania on 2018-12-04.
 */
public class MethodArgumentNotValidException extends RuntimeException {
    public MethodArgumentNotValidException(String message) {
        super(message);
    }
}
