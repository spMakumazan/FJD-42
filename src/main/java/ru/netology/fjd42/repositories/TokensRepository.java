package ru.netology.fjd42.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.netology.fjd42.model.Token;

public interface TokensRepository extends CrudRepository<Token, String> {
}
