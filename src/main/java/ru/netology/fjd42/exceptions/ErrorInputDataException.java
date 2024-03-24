package ru.netology.fjd42.exceptions;

public class ErrorInputDataException extends RuntimeException {
    public ErrorInputDataException() {
        super();
    }

    public ErrorInputDataException(String message) {
        super(message);
    }

    public ErrorInputDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorInputDataException(Throwable cause) {
        super(cause);
    }

    protected ErrorInputDataException(String message, Throwable cause,
                                      boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
