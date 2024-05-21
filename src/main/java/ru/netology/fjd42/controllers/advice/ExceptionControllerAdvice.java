package ru.netology.fjd42.controllers.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.netology.fjd42.exceptions.*;
import ru.netology.fjd42.schemas.ErrorSchema;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorSchema> bcHandler(BadCredentialsException exception) {
        return new ResponseEntity<>(new ErrorSchema(exception.getMessage(), 400), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErrorInputDataException.class)
    public ResponseEntity<ErrorSchema> eidHandler(ErrorInputDataException exception) {
        return new ResponseEntity<>(new ErrorSchema(exception.getMessage(), 400), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedErrorException.class)
    public ResponseEntity<ErrorSchema> ueHandler(UnauthorizedErrorException exception) {
        return new ResponseEntity<>(new ErrorSchema(exception.getMessage(), 401), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ErrorDeleteFileException.class)
    public ResponseEntity<ErrorSchema> edfHandler(ErrorDeleteFileException exception) {
        return new ResponseEntity<>(new ErrorSchema(exception.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ErrorUploadFileException.class)
    public ResponseEntity<ErrorSchema> eufHandler(ErrorUploadFileException exception) {
        return new ResponseEntity<>(new ErrorSchema(exception.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ErrorGettingFileListException.class)
    public ResponseEntity<ErrorSchema> egflHandler(ErrorGettingFileListException exception) {
        return new ResponseEntity<>(new ErrorSchema(exception.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ErrorDownloadFileException.class)
    public ResponseEntity<ErrorSchema> edlfHandler(ErrorDownloadFileException exception) {
        return new ResponseEntity<>(new ErrorSchema(exception.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}