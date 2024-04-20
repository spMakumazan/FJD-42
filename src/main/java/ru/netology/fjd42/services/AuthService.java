package ru.netology.fjd42.services;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.netology.fjd42.model.Token;
import ru.netology.fjd42.model.User;
import ru.netology.fjd42.repositories.TokensRepository;
import ru.netology.fjd42.schemas.AuthTokenSchema;
import ru.netology.fjd42.schemas.UserSchema;
import ru.netology.fjd42.security.jwt.Utils;

@Service
@AllArgsConstructor
public class AuthService {

    private UsersService usersService;
    private AuthenticationManager authenticationManager;
    private TokensRepository tokensRepository;
    private Utils utils;

    public AuthTokenSchema authorizationMethod(UserSchema userSchema) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userSchema.getLogin(), userSchema.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = utils.generateToken(authentication);
        User user = usersService.loadUserByUsername(userSchema.getLogin());
        tokensRepository.save(new Token(jwt, user));
        return new AuthTokenSchema(jwt);
    }

    public void logout(String authToken) {
        String jwtToken = authToken.substring(7);
        tokensRepository.deleteById(jwtToken);
    }
}
