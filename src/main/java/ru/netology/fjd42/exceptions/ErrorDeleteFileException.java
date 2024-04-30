package ru.netology.fjd42.exceptions;

public class ErrorDeleteFileException extends RuntimeException {

    public ErrorDeleteFileException() {
        super();
    }

    public ErrorDeleteFileException(String message) {
        super(message);
    }

    public ErrorDeleteFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorDeleteFileException(Throwable cause) {
        super(cause);
    }

    protected ErrorDeleteFileException(String message, Throwable cause,
                                       boolean enableSuppression,
                                       boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
