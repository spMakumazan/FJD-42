package ru.netology.fjd42.schemas;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileSizeSchema {
    private String filename;
    private int size;
}
