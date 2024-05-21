package ru.netology.fjd42.controllers.advice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.netology.fjd42.schemas.ErrorSchema;

import static ru.netology.fjd42.DataForTests.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ExceptionControllerAdviceTest {

    @InjectMocks
    ExceptionControllerAdvice exceptionControllerAdvice;

    @Test
    public void bcHandlerTest() {
        ResponseEntity<ErrorSchema> result = exceptionControllerAdvice.bcHandler(BAD_CREDENTIALS_EXCEPTION);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertEquals(BAD_CREDENTIALS_EXCEPTION_SCHEMA, result.getBody());
    }

    @Test
    public void eidHandlerTest() {
        ResponseEntity<ErrorSchema> result = exceptionControllerAdvice.eidHandler(ERROR_INPUT_DATA_EXCEPTION);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertEquals(ERROR_INPUT_DATA_EXCEPTION_SCHEMA, result.getBody());
    }

    @Test
    public void ueHandlerTest() {
        ResponseEntity<ErrorSchema> result = exceptionControllerAdvice.ueHandler(UNAUTHORIZED_ERROR_EXCEPTION);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
        Assertions.assertEquals(UNAUTHORIZED_ERROR_EXCEPTION_SCHEMA, result.getBody());
    }

    @Test
    public void edfHandlerTest() {
        ResponseEntity<ErrorSchema> result = exceptionControllerAdvice.edfHandler(ERROR_DELETE_FILE_EXCEPTION);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertEquals(ERROR_DELETE_FILE_EXCEPTION_SCHEMA, result.getBody());
    }

    @Test
    public void eufHandlerTest() {
        ResponseEntity<ErrorSchema> result = exceptionControllerAdvice.eufHandler(ERROR_UPLOAD_FILE_EXCEPTION);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertEquals(ERROR_UPLOAD_FILE_EXCEPTION_SCHEMA, result.getBody());
    }

    @Test
    public void egflHandlerTest() {
        ResponseEntity<ErrorSchema> result = exceptionControllerAdvice.egflHandler(ERROR_GETTING_FILE_LIST_EXCEPTION);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertEquals(ERROR_GETTING_FILE_LIST_EXCEPTION_SCHEMA, result.getBody());
    }

    @Test
    public void edlfHandlerTest() {
        ResponseEntity<ErrorSchema> result = exceptionControllerAdvice.edlfHandler(ERROR_DOWNLOAD_FILE_EXCEPTION);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertEquals(ERROR_DOWNLOAD_FILE_EXCEPTION_SCHEMA, result.getBody());
    }
}
