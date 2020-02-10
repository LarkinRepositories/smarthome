package ru.innopolis.authService.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.innopolis.authService.model.Role;
import ru.innopolis.authService.model.Status;
import ru.innopolis.authService.model.User;
import ru.innopolis.authService.repository.RoleRepository;
import ru.innopolis.authService.repository.UserRepository;
import ru.innopolis.authService.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);
        User registeredUser = userRepository.save(user);
        log.info("IN register - user {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        log.info("IN getAll - {} users found", users.size());
        return users;
    }

    @Override
    public User findByUsername(String username) {
        User result  = userRepository.findByUsername(username);
        if (result == null) {
            log.warn("In findByUsername - user == null");
            return null;
        }
        log.info("In findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);
        if (result == null) {
            log.warn("In findById no user found with id {}", id);
            return null;
        }
        log.info("In findById user {} found by id {}", result, id);
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("In delete user with id {} successfully deleted", id);
    }
}
