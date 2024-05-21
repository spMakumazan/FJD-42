package ru.netology.fjd42.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.netology.fjd42.exceptions.*;
import ru.netology.fjd42.repositories.FilesRepository;
import ru.netology.fjd42.repositories.TokensRepository;
import ru.netology.fjd42.schemas.FileSizeSchema;

import java.util.List;
import java.util.Optional;

import static ru.netology.fjd42.DataForTests.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class FilesServiceTest {

    @InjectMocks
    FilesService filesService;

    @Mock
    FilesRepository filesRepository;

    @Mock
    TokensRepository tokensRepository;

    @BeforeEach
    public void beforeEach() {
        Mockito.when(tokensRepository.findById(AUTH_TOKEN)).thenReturn(Optional.of(TOKEN));
    }

    @Test
    public void uploadFileTest() {
        filesService.uploadFile(BEARER_AUTH_TOKEN, FILENAME, MULTIPART_FILE);
        Mockito.verify(filesRepository, Mockito.only()).save(FILE);
    }

    @Test
    public void uploadFileUnauthTest() {
        Assertions.assertThrows(UnauthorizedErrorException.class,
                () -> filesService.uploadFile(BEARER_UNAUTH_TOKEN, FILENAME, MULTIPART_FILE));
    }

    @Test
    public void deleteFileTest() {
        Mockito.when(filesRepository.findByFilenameAndOwner(FILENAME, USER)).thenReturn(Optional.of(FILE));
        filesService.deleteFile(BEARER_AUTH_TOKEN, FILENAME);
        Mockito.verify(filesRepository, Mockito.times(1)).delete(FILE);
    }

    @Test
    public void deleteFileUnauthTest() {
        Mockito.when(filesRepository.findByFilenameAndOwner(FILENAME, USER)).thenReturn(Optional.of(FILE));
        Assertions.assertThrows(UnauthorizedErrorException.class,
                () -> filesService.deleteFile(BEARER_UNAUTH_TOKEN, FILENAME));
    }

    @Test
    public void deleteFileErrorInputDataTest() {
        Mockito.when(filesRepository.findByFilenameAndOwner(FILENAME, USER)).thenReturn(Optional.of(FILE));
        Assertions.assertThrows(ErrorInputDataException.class,
                () -> filesService.deleteFile(BEARER_AUTH_TOKEN, null));
    }

    @Test
    public void deleteFileErrorDeleteTest() {
        Mockito.when(filesRepository.findByFilenameAndOwner(FILENAME, USER)).thenReturn(Optional.of(FILE));
        Assertions.assertThrows(ErrorDeleteFileException.class,
                () -> filesService.deleteFile(BEARER_AUTH_TOKEN, BAD_FILENAME));
    }

    @Test
    public void downloadFileTest() {
        Mockito.when(filesRepository.findByFilenameAndOwner(FILENAME, USER)).thenReturn(Optional.of(FILE));
        byte[] result = filesService.downloadFile(BEARER_AUTH_TOKEN, FILENAME);
        Assertions.assertEquals(CONTENT, result);
    }

    @Test
    public void downloadFileUnauthTest() {
        Mockito.when(filesRepository.findByFilenameAndOwner(FILENAME, USER)).thenReturn(Optional.of(FILE));
        Assertions.assertThrows(UnauthorizedErrorException.class,
                () -> filesService.downloadFile(BEARER_UNAUTH_TOKEN, FILENAME));
    }

    @Test
    public void downloadFileErrorInputDataTest() {
        Mockito.when(filesRepository.findByFilenameAndOwner(FILENAME, USER)).thenReturn(Optional.of(FILE));
        Assertions.assertThrows(ErrorInputDataException.class,
                () -> filesService.downloadFile(BEARER_AUTH_TOKEN, null));
    }

    @Test
    public void downloadFileErrorDownloadTest() {
        Mockito.when(filesRepository.findByFilenameAndOwner(FILENAME, USER)).thenReturn(Optional.of(FILE));
        Assertions.assertThrows(ErrorDownloadFileException.class,
                () -> filesService.downloadFile(BEARER_AUTH_TOKEN, BAD_FILENAME));
    }

    @Test
    public void editFilenameTest() {
        Mockito.when(filesRepository.findByFilenameAndOwner(FILENAME, USER)).thenReturn(Optional.of(FILE));
        filesService.editFilename(BEARER_AUTH_TOKEN, FILENAME, FILENAME_SCHEMA);
        Mockito.verify(filesRepository, Mockito.times(1)).save(NEW_FILE);
    }

    @Test
    public void editFilenameUnauthTest() {
        Mockito.when(filesRepository.findByFilenameAndOwner(FILENAME, USER)).thenReturn(Optional.of(FILE));
        Assertions.assertThrows(UnauthorizedErrorException.class,
                () -> filesService.editFilename(BEARER_UNAUTH_TOKEN, FILENAME, FILENAME_SCHEMA));
    }

    @Test
    public void editFilenameErrorInputDataTest() {
        Mockito.when(filesRepository.findByFilenameAndOwner(FILENAME, USER)).thenReturn(Optional.of(FILE));
        Assertions.assertThrows(ErrorInputDataException.class,
                () -> filesService.editFilename(BEARER_AUTH_TOKEN, null, FILENAME_SCHEMA));
    }

    @Test
    public void editFilenameErrorUploadTest() {
        Mockito.when(filesRepository.findByFilenameAndOwner(FILENAME, USER)).thenReturn(Optional.of(FILE));
        Assertions.assertThrows(ErrorUploadFileException.class,
                () -> filesService.editFilename(BEARER_AUTH_TOKEN, BAD_FILENAME, FILENAME_SCHEMA));
    }

    @Test
    public void getAllFilesTest() {
        Mockito.when(filesRepository.findAllByOwner(USER)).thenReturn(FILE_LIST);
        List<FileSizeSchema> result = filesService.getAllFiles(BEARER_AUTH_TOKEN, 3);
        Assertions.assertEquals(FILE_SIZE_SCHEMA_LIST, result);
    }

    @Test
    public void getAllFilesUnauthTest() {
        Mockito.when(filesRepository.findAllByOwner(USER)).thenReturn(FILE_LIST);
        Assertions.assertThrows(UnauthorizedErrorException.class,
                () -> filesService.getAllFiles(BEARER_UNAUTH_TOKEN, 3));
    }

    @Test
    public void getAllFilesErrorInputDataTest() {
        Mockito.when(filesRepository.findAllByOwner(USER)).thenReturn(FILE_LIST);
        Assertions.assertThrows(ErrorInputDataException.class,
                () -> filesService.getAllFiles(BEARER_AUTH_TOKEN, -3));
    }
}
