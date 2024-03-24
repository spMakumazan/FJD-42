package ru.netology.fjd42.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.netology.fjd42.model.User;

import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<User, String> {
}
