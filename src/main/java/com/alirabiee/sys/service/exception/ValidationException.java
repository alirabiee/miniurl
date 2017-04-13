package com.alirabiee.sys.service.exception;

/**
 * Created by ali.
 */
public class ValidationException extends Exception {
    public ValidationException() {
        super();
    }

    public ValidationException(final String message) {
        super( message );
    }

    public ValidationException(final String message, final Throwable cause) {
        super( message, cause );
    }

    public ValidationException(final Throwable cause) {
        super( cause );
    }

    protected ValidationException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super( message, cause, enableSuppression, writableStackTrace );
    }
}
