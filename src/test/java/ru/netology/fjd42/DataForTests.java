package ru.netology.fjd42;

import org.springframework.mock.web.MockMultipartFile;
import ru.netology.fjd42.exceptions.*;
import ru.netology.fjd42.model.File;
import ru.netology.fjd42.model.Token;
import ru.netology.fjd42.model.User;
import ru.netology.fjd42.schemas.*;

import java.util.List;

public class DataForTests {

    public static final String USERNAME = "username";
    public static final String BAD_USERNAME = "bad-username";
    public static final String PASSWORD = "password";
    public static final UserSchema USER_SCHEMA = new UserSchema(USERNAME, PASSWORD);
    public static final String JSON_USER_SCHEMA = """
            {
                "login": "username",
                "password": "password"
            }
            """;
    public static final User USER = new User(USERNAME, PASSWORD);

    public static final String AUTH_TOKEN = "auth-token";
    public static final AuthTokenSchema AUTH_TOKEN_SCHEMA = new AuthTokenSchema(AUTH_TOKEN);
    public static final String JSON_AUTH_TOKEN_SCHEMA = """
            {
                "auth-token": "auth-token"
            }
            """;
    public static final Token TOKEN = new Token(AUTH_TOKEN, USER);
    public static final String BEARER_AUTH_TOKEN = "Bearer auth-token";
    public static final String BEARER_UNAUTH_TOKEN = "Bearer unauth-token";

    public static final String FILENAME = "filename";
    public static final String BAD_FILENAME = "bad-filename";
    public static final byte[] CONTENT = FILENAME.getBytes();
    public static final File FILE = new File(null, FILENAME, CONTENT, USER);
    public static final MockMultipartFile MULTIPART_FILE = new MockMultipartFile(FILENAME, CONTENT);
    public static final String NEW_FILENAME = "new-filename";
    public static final FileNameSchema FILENAME_SCHEMA = new FileNameSchema(NEW_FILENAME);
    public static final String JSON_FILENAME_SCHEMA = """
            {
                "filename": "new-filename"
            }
            """;
    public static final File NEW_FILE = new File(null, NEW_FILENAME, CONTENT, USER);
    public static final List<File> FILE_LIST = List.of(FILE);
    public static final FileSizeSchema FILE_SIZE_SCHEMA = new FileSizeSchema(FILENAME, CONTENT.length);
    public static final List<FileSizeSchema> FILE_SIZE_SCHEMA_LIST = List.of(FILE_SIZE_SCHEMA);
    public static final String JSON_FILE_SIZE_SCHEMA_LIST = """
            [
                {
                    "filename": "filename",
                    "size": 8 
                }               
            ]
            """;

    public static final String BAD_CREDENTIALS = "Bad credentials";
    public static final BadCredentialsException BAD_CREDENTIALS_EXCEPTION =
            new BadCredentialsException(BAD_CREDENTIALS);
    public static final ErrorSchema BAD_CREDENTIALS_EXCEPTION_SCHEMA = new ErrorSchema(BAD_CREDENTIALS, 400);

    public static final String ERROR_INPUT_DATA = "Error input data";
    public static final ErrorInputDataException ERROR_INPUT_DATA_EXCEPTION =
            new ErrorInputDataException(ERROR_INPUT_DATA);
    public static final ErrorSchema ERROR_INPUT_DATA_EXCEPTION_SCHEMA = new ErrorSchema(ERROR_INPUT_DATA, 400);

    public static final String UNAUTHORIZED_ERROR = "Unauthorized error";
    public static final UnauthorizedErrorException UNAUTHORIZED_ERROR_EXCEPTION =
            new UnauthorizedErrorException(UNAUTHORIZED_ERROR);
    public static final ErrorSchema UNAUTHORIZED_ERROR_EXCEPTION_SCHEMA = new ErrorSchema(UNAUTHORIZED_ERROR, 401);

    public static final String ERROR_DELETE_FILE = "Error delete file";
    public static final ErrorDeleteFileException ERROR_DELETE_FILE_EXCEPTION =
            new ErrorDeleteFileException(ERROR_DELETE_FILE);
    public static final ErrorSchema ERROR_DELETE_FILE_EXCEPTION_SCHEMA = new ErrorSchema(ERROR_DELETE_FILE, 500);

    public static final String ERROR_UPLOAD_FILE = "Error upload file";
    public static final ErrorUploadFileException ERROR_UPLOAD_FILE_EXCEPTION =
            new ErrorUploadFileException(ERROR_UPLOAD_FILE);
    public static final ErrorSchema ERROR_UPLOAD_FILE_EXCEPTION_SCHEMA = new ErrorSchema(ERROR_UPLOAD_FILE, 500);

    public static final String ERROR_GETTING_FILE_LIST = "Error getting file list";
    public static final ErrorGettingFileListException ERROR_GETTING_FILE_LIST_EXCEPTION =
            new ErrorGettingFileListException(ERROR_GETTING_FILE_LIST);
    public static final ErrorSchema ERROR_GETTING_FILE_LIST_EXCEPTION_SCHEMA = new ErrorSchema(ERROR_GETTING_FILE_LIST, 500);

    public static final String ERROR_DOWNLOAD_FILE = "Error download file";
    public static final ErrorDownloadFileException ERROR_DOWNLOAD_FILE_EXCEPTION =
            new ErrorDownloadFileException(ERROR_DOWNLOAD_FILE);
    public static final ErrorSchema ERROR_DOWNLOAD_FILE_EXCEPTION_SCHEMA = new ErrorSchema(ERROR_DOWNLOAD_FILE, 500);
}
