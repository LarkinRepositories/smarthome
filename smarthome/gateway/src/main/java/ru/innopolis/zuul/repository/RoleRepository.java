package ru.innopolis.zuul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.zuul.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
