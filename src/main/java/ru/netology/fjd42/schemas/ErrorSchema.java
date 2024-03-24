package ru.netology.fjd42.schemas;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorSchema {
    private String message;
    private int id;
}
