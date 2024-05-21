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
import ru.netology.fjd42.exceptions.BadCredentialsException;
import ru.netology.fjd42.model.User;
import ru.netology.fjd42.repositories.UsersRepository;

import java.util.Optional;

import static ru.netology.fjd42.DataForTests.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UsersServiceTest {

    @InjectMocks
    private UsersService usersService;

    @Mock
    private UsersRepository usersRepository;

    @Test
    public void loadUserByUsernameTest() {
        Mockito.when(usersRepository.findById(USERNAME)).thenReturn(Optional.of(USER));
        User result = usersService.loadUserByUsername(USERNAME);
        Assertions.assertEquals(USER, result);
    }

    @Test
    public void loadUserByUsernameWithExceptionTest() {
        Mockito.when(usersRepository.findById(USERNAME)).thenReturn(Optional.of(USER));
        Assertions.assertThrows(BadCredentialsException.class,
                () -> usersService.loadUserByUsername(BAD_USERNAME));
    }
}
