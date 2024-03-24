package ru.netology.fjd42.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.netology.fjd42.model.File;
import ru.netology.fjd42.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilesRepository extends CrudRepository<File, Long> {

    Optional<File> findByFilenameAndOwner(String filename, User user);

    List<File> findAllByOwner(User user);
}
