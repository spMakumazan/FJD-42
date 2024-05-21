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
import ru.netology.fjd42.schemas.AuthTokenSchema;
import ru.netology.fjd42.services.AuthService;

import static ru.netology.fjd42.DataForTests.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @Test
    public void authorizationMethodTest() {
        Mockito.when(authService.authorizationMethod(USER_SCHEMA)).thenReturn(AUTH_TOKEN_SCHEMA);
        ResponseEntity<AuthTokenSchema> result = authController.authorizationMethod(USER_SCHEMA);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(AUTH_TOKEN_SCHEMA, result.getBody());
    }

    @Test
    public void logoutTest() {
        ResponseEntity<?> result = authController.logout(BEARER_AUTH_TOKEN);
        Mockito.verify(authService, Mockito.only()).logout(BEARER_AUTH_TOKEN);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}
