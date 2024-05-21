package ru.netology.fjd42.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.netology.fjd42.model.File;

import java.util.List;
import java.util.Optional;

import static ru.netology.fjd42.DataForTests.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FileRepositoryTest {

    @Autowired
    private FilesRepository filesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @BeforeEach
    public void beforeEach() {
        filesRepository.deleteAll();
        usersRepository.deleteAll();
        usersRepository.save(USER);
        filesRepository.save(FILE);
    }

    @Test
    public void findByFilenameAndOwnerTest() {
        Optional<File> result = filesRepository.findByFilenameAndOwner(FILENAME, USER);
        Assertions.assertEquals(Optional.of(FILE), result);
    }

    @Test
    public void findAllByOwnerTest() {
        List<File> result = filesRepository.findAllByOwner(USER);
        Assertions.assertEquals(FILE_LIST, result);
    }

}
