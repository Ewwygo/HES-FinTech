package com.hes.fintech.usercontrol.exception;

public class SuchUserAlreadyExistException extends Exception {

    public SuchUserAlreadyExistException() {
        super();
    }

    public SuchUserAlreadyExistException(final String message) {
        super(message);
    }
}
