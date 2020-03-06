package ru.innopolis.zuul.rest;

import dto.authservice.entities.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.zuul.service.UserService;


@RestController
@RequestMapping("/api")
public class CrudController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/add/")
    public UserDto addUser(@RequestBody UserDto userDto) {
       return userService.register(userDto);
    }

    @PostMapping("/user/update/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserDto userDto ) {
        userDto.setId(id);
        return userService.update(userDto);
    }

    @GetMapping("/user/get/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/user/delete/{id}")
    public boolean delete(@PathVariable Long id, @RequestBody UserDto userDto) {
        userDto.setId(id);
        return userService.delete(userDto);
    }
}
