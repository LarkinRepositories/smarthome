package ru.innopolis.authService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.authService.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
