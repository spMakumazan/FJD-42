package ru.netology.fjd42.exceptions;

public class ErrorGettingFileListException extends RuntimeException {

    public ErrorGettingFileListException() {
        super();
    }

    public ErrorGettingFileListException(String message) {
        super(message);
    }

    public ErrorGettingFileListException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorGettingFileListException(Throwable cause) {
        super(cause);
    }

    protected ErrorGettingFileListException(String message, Throwable cause,
                                            boolean enableSuppression,
                                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
