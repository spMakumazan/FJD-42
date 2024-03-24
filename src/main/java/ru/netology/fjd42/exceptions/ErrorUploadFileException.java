package ru.netology.fjd42.exceptions;

public class ErrorUploadFileException extends RuntimeException {

    public ErrorUploadFileException() {
        super();
    }

    public ErrorUploadFileException(String message) {
        super(message);
    }

    public ErrorUploadFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorUploadFileException(Throwable cause) {
        super(cause);
    }

    protected ErrorUploadFileException(String message, Throwable cause,
                                       boolean enableSuppression,
                                       boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
