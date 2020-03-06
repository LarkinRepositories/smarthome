package ru.innopolis.authService.service.impl;

import dto.authservice.entities.UserDto;
import dto.base.Status;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.innopolis.authService.mapper.UserMapper;
import ru.innopolis.authService.model.User;
import ru.innopolis.authService.repository.RoleRepository;
import ru.innopolis.authService.repository.UserRepository;
import ru.innopolis.authService.service.UserService;

import java.time.LocalDateTime;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private UserMapper mapper;
    private UserRepository userRepository;
//    private RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
//    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
    public UserServiceImpl(UserMapper mapper, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto register(UserDto userDto) {
        userDto.setCreated(LocalDateTime.now());
        userDto.setUpdated(LocalDateTime.now());
        userDto.setStatus(Status.ACTIVE);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return mapper.toDto(userRepository.save(mapper.toEntity(userDto)));
    }

//    @Override
//    public UserDto findByUserName(String username) {
//        return null;
//    }

    @Override
    public UserDto findById(Long id) {
        return null;
    }

    @Override
    public UserDto update(UserDto userDto) {
        userDto.setUpdated(LocalDateTime.now());
        return mapper.toDto(userRepository.save(mapper.toEntity(userDto)));
    }

    @Override
    public boolean delete(UserDto userDto) {
        userDto.setStatus(Status.DELETED);
        userDto.setUpdated(LocalDateTime.now());
        mapper.toDto(userRepository.save(mapper.toEntity(userDto)));
        return true;
    }


//    @Override
//    public User register(User user) {
//        Role roleUser = roleRepository.findByName("ROLE_USER");
//        List<Role> userRoles = new ArrayList<>();
//        userRoles.add(roleUser);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRoles(userRoles);
//        user.setCreated(LocalDateTime.now());
//        user.setUpdated(LocalDateTime.now());
//        user.setStatus(Status.ACTIVE);
//        User registeredUser = userRepository.save(user);
//        log.info("IN register - user {} successfully registered", registeredUser);
//        return registeredUser;
//    }
//
//    @Override
//    public List<User> getAll() {
//        List<User> users = userRepository.findAll();
//        log.info("IN getAll - {} users found", users.size());
//        return users;
//    }
//
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
//
//    @Override
//    public User findById(Long id) {
//        User result = userRepository.findById(id).orElse(null);
//        if (result == null) {
//            log.warn("In findById no user found with id {}", id);
//            return null;
//        }
//        log.info("In findById user {} found by id {}", result, id);
//        return result;
//    }
//
//
//    @Override
//    public boolean update(User user) {
//        return false;
//    }
//
//    @Override
//    public boolean delete(Long id) {
//        User user = userRepository.findById(id).orElse(null);
//        if (user != null) {
//            user.setStatus(Status.DELETED);
//            user.setUpdated(LocalDateTime.now());
//            log.info("In delete user with id {} successfully deleted", id);
//            return true;
//        }
//        log.warn("In delete user with id {} not found", id);
//        return false;
//    }
}
