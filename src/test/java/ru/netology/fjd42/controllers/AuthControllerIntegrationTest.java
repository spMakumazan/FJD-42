package ru.netology.fjd42.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.netology.fjd42.security.jwt.Utils;
import ru.netology.fjd42.services.AuthService;
import ru.netology.fjd42.services.UsersService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.netology.fjd42.DataForTests.*;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class AuthControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @MockBean
    private Utils utils;

    @MockBean
    private UsersService usersService;

    @Test
    public void authorizationMethodTest() throws Exception {
        Mockito.when(authService.authorizationMethod(USER_SCHEMA)).thenReturn(AUTH_TOKEN_SCHEMA);
        mockMvc.perform(post("/cloud/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON_USER_SCHEMA))
                .andExpectAll(
                        status().isOk(),
                        content().json(JSON_AUTH_TOKEN_SCHEMA)
                );
    }

    @Test
    public void logoutTest() throws Exception {
        Mockito.when(utils.getUsernameFromToken(AUTH_TOKEN)).thenReturn(USERNAME);
        Mockito.when(usersService.loadUserByUsername(USERNAME)).thenReturn(USER);
        mockMvc.perform(post("/cloud/logout")
                        .header("auth-token", BEARER_AUTH_TOKEN))
                .andExpect(status().isOk());
        Mockito.verify(authService, Mockito.only()).logout(BEARER_AUTH_TOKEN);
    }
}
