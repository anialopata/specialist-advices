package com.anialopata.registration.exception;

/**
 * Created by Ania on 2018-12-04.
 */
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }

}
