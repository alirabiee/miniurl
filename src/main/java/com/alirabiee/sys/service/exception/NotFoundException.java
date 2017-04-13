package com.alirabiee.sys.service.exception;

/**
 * Created by ali.
 */
public class NotFoundException extends Exception {
    public NotFoundException() {
        super();
    }

    public NotFoundException(final String message) {
        super( message );
    }

    public NotFoundException(final String message, final Throwable cause) {
        super( message, cause );
    }

    public NotFoundException(final Throwable cause) {
        super( cause );
    }

    protected NotFoundException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super( message, cause, enableSuppression, writableStackTrace );
    }
}
