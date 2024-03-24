package ru.netology.fjd42.schemas;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileSchema {
    private String hash;
    private String file;
}
