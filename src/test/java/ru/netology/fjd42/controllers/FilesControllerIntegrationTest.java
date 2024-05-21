package ru.netology.fjd42.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.netology.fjd42.security.jwt.Utils;
import ru.netology.fjd42.services.FilesService;
import ru.netology.fjd42.services.UsersService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.netology.fjd42.DataForTests.*;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class FilesControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    FilesService filesService;

    @MockBean
    private Utils utils;

    @MockBean
    private UsersService usersService;

    @BeforeEach
    public void beforeEach() {
        Mockito.when(utils.getUsernameFromToken(AUTH_TOKEN)).thenReturn(USERNAME);
        Mockito.when(usersService.loadUserByUsername(USERNAME)).thenReturn(USER);
    }

    @Test
    public void uploadFileTest() throws Exception {
        mockMvc.perform(multipart("/cloud/file")
                        .file(MULTIPART_FILE)
                        .header("auth-token", BEARER_AUTH_TOKEN)
                        .param("filename", FILENAME))
                .andExpect(status().isOk());
        Mockito.verify(filesService, Mockito.only()).uploadFile(BEARER_AUTH_TOKEN, FILENAME, MULTIPART_FILE);
    }

    @Test
    public void deleteFileTest() throws Exception {
        mockMvc.perform(delete("/cloud/file")
                        .header("auth-token", BEARER_AUTH_TOKEN)
                        .param("filename", FILENAME))
                .andExpect(status().isOk());
        Mockito.verify(filesService, Mockito.only()).deleteFile(BEARER_AUTH_TOKEN, FILENAME);
    }

    @Test
    public void downloadFileTest() throws Exception {
        Mockito.when(filesService.downloadFile(BEARER_AUTH_TOKEN, FILENAME)).thenReturn(CONTENT);
        mockMvc.perform(get("/cloud/file")
                        .header("auth-token", BEARER_AUTH_TOKEN)
                        .param("filename", FILENAME))
                .andExpectAll(
                        status().isOk(),
                        content().bytes(CONTENT));
    }

    @Test
    public void editFilenameTest() throws Exception {
        mockMvc.perform(put("/cloud/file")
                        .header("auth-token", BEARER_AUTH_TOKEN)
                        .param("filename", FILENAME)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON_FILENAME_SCHEMA))
                .andExpect(status().isOk());
        Mockito.verify(filesService, Mockito.only()).editFilename(BEARER_AUTH_TOKEN, FILENAME, FILENAME_SCHEMA);
    }

    @Test
    public void getAllFilesTest() throws Exception {
        Mockito.when(filesService.getAllFiles(BEARER_AUTH_TOKEN, 3)).thenReturn(FILE_SIZE_SCHEMA_LIST);
        mockMvc.perform(get("/cloud/list")
                        .header("auth-token", BEARER_AUTH_TOKEN)
                        .param("limit", Integer.toString(3)))
                .andExpectAll(
                        status().isOk(),
                        content().json(JSON_FILE_SIZE_SCHEMA_LIST)
                );
    }
}
