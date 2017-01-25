package com.epam.skovalkov.exception;

/**
 * Created by Sergei_Kovalkov on 1/24/2017.
 */
public class ValidationFailedException extends Throwable{

    public ValidationFailedException() {
    }

    public ValidationFailedException(String message) {
        super(message);
    }

    public ValidationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationFailedException(Throwable cause) {
        super(cause);
    }

}
