package ru.netology.fjd42.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.fjd42.schemas.AuthTokenSchema;
import ru.netology.fjd42.schemas.UserSchema;
import ru.netology.fjd42.services.AuthService;

@RestController
@AllArgsConstructor
@RequestMapping("/cloud")
public class AuthController {

    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthTokenSchema> authorizationMethod(@RequestBody UserSchema userSchema) {
        AuthTokenSchema authTokenSchema = authService.authorizationMethod(userSchema);
        return ResponseEntity.ok(authTokenSchema);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("auth-token") String authToken) {
        authService.logout(authToken);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
