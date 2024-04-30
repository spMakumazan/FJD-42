package ru.netology.fjd42.exceptions;

public class UnauthorizedErrorException extends RuntimeException {

    public UnauthorizedErrorException() {
        super();
    }

    public UnauthorizedErrorException(String message) {
        super(message);
    }

    public UnauthorizedErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedErrorException(Throwable cause) {
        super(cause);
    }

    protected UnauthorizedErrorException(String message, Throwable cause,
                                         boolean enableSuppression,
                                         boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
