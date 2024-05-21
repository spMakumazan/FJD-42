package ru.netology.fjd42.exceptions;

public class ErrorDownloadFileException extends RuntimeException {

    public ErrorDownloadFileException() {
        super();
    }

    public ErrorDownloadFileException(String message) {
        super(message);
    }

    public ErrorDownloadFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorDownloadFileException(Throwable cause) {
        super(cause);
    }

    protected ErrorDownloadFileException(String message, Throwable cause,
                                         boolean enableSuppression,
                                         boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
