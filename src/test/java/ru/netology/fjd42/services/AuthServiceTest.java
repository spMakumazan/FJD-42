package ru.netology.fjd42.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.authentication.AuthenticationManager;
import ru.netology.fjd42.repositories.TokensRepository;
import ru.netology.fjd42.schemas.AuthTokenSchema;
import ru.netology.fjd42.security.jwt.Utils;

import static ru.netology.fjd42.DataForTests.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UsersService usersService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokensRepository tokensRepository;

    @Mock
    private Utils utils;

    @Test
    public void authorizationMethodTest() {
        Mockito.when(utils.generateToken(Mockito.any())).thenReturn(AUTH_TOKEN);
        Mockito.when(usersService.loadUserByUsername(USERNAME)).thenReturn(USER);

        AuthTokenSchema result = authService.authorizationMethod(USER_SCHEMA);

        Assertions.assertEquals(AUTH_TOKEN_SCHEMA, result);
        Mockito.verify(authenticationManager, Mockito.only()).authenticate(Mockito.any());
        Mockito.verify(tokensRepository, Mockito.only()).save(TOKEN);
    }

    @Test
    public void logoutTest() {
        authService.logout(BEARER_AUTH_TOKEN);
        Mockito.verify(tokensRepository, Mockito.only()).deleteById(AUTH_TOKEN);
    }
}
