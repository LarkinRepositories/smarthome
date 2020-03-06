package ru.innopolis.zuul.service;

import dto.authservice.entities.UserDto;
import ru.innopolis.zuul.model.User;


public interface UserService {
//    User register(User user);
//    List<User> getAll();
//    User findByUsername(String username);
//    User findById(Long id);
//    boolean update(User user);
//    boolean delete(Long id);

    UserDto register(UserDto userDto);
    User findByUsername(String username);
    UserDto findById(Long id);
    UserDto update(UserDto userDto);
    boolean delete(UserDto userDto);
}
