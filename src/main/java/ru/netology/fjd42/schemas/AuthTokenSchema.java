package ru.netology.fjd42.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthTokenSchema {

    @JsonProperty("auth-token")
    private String authToken;
}
