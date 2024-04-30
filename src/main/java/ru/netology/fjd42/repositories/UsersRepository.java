package ru.netology.fjd42.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.netology.fjd42.model.User;

@Repository
public interface UsersRepository extends CrudRepository<User, String> {
}
