package com.anialopata.registration.exception;

/**
 * Created by Ania on 2018-12-04.
 */
public class DateIsBeforeNowException extends RuntimeException {
    public DateIsBeforeNowException(String message) {
        super(message);
    }
}
