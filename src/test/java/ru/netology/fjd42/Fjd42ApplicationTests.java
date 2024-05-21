package ru.netology.fjd42;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
class Fjd42ApplicationTests {

    @Container
    public static GenericContainer<?> server = new GenericContainer<>("back");

    @Test
    void loadServer() {
        Assertions.assertTrue(server.isRunning());
    }
}
