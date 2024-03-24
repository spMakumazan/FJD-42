package ru.netology.fjd42.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.netology.fjd42.exceptions.BadCredentialsException;
import ru.netology.fjd42.model.User;
import ru.netology.fjd42.repositories.UsersRepository;

@Service
@AllArgsConstructor
public class UsersService implements UserDetailsService {

    private UsersRepository usersRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findById(username).orElseThrow(
                () -> new BadCredentialsException("Bad credentials")
        );
    }
}
