package ru.netology.fjd42.schemas;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSchema {
    private String login;
    private String password;
}
