package ru.innopolis.authService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.authService.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
}
