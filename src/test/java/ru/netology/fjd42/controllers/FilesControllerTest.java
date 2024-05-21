package ru.netology.fjd42.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.netology.fjd42.schemas.FileSizeSchema;
import ru.netology.fjd42.services.FilesService;

import java.util.List;

import static ru.netology.fjd42.DataForTests.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class FilesControllerTest {

    @Mock
    private FilesService filesService;

    @InjectMocks
    private FilesController filesController;

    @Test
    public void uploadFileTest() {
        ResponseEntity<?> result = filesController.uploadFile(BEARER_AUTH_TOKEN, FILENAME, MULTIPART_FILE);
        Mockito.verify(filesService, Mockito.only()).uploadFile(BEARER_AUTH_TOKEN, FILENAME, MULTIPART_FILE);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void deleteFileTest() {
        ResponseEntity<?> result = filesController.deleteFile(BEARER_AUTH_TOKEN, FILENAME);
        Mockito.verify(filesService, Mockito.only()).deleteFile(BEARER_AUTH_TOKEN, FILENAME);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void downloadFileTest() {
        Mockito.when(filesService.downloadFile(BEARER_AUTH_TOKEN, FILENAME)).thenReturn(CONTENT);
        ResponseEntity<byte[]> result = filesController.downloadFile(BEARER_AUTH_TOKEN, FILENAME);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(CONTENT, result.getBody());
    }

    @Test
    public void editFilenameTest() {
        ResponseEntity<?> result = filesController.editFilename(BEARER_AUTH_TOKEN, FILENAME, FILENAME_SCHEMA);
        Mockito.verify(filesService, Mockito.only()).editFilename(BEARER_AUTH_TOKEN, FILENAME, FILENAME_SCHEMA);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getAllFilesTest() {
        Mockito.when(filesService.getAllFiles(BEARER_AUTH_TOKEN, 3)).thenReturn(FILE_SIZE_SCHEMA_LIST);
        ResponseEntity<List<FileSizeSchema>> result = filesController.getAllFiles(BEARER_AUTH_TOKEN, 3);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(FILE_SIZE_SCHEMA_LIST, result.getBody());
    }
}
