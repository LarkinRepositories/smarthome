package ru.innopolis.zuul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.zuul.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
}
