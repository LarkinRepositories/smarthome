package ru.innopolis.webService.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * scenarios
 */
@RestController
@RequestMapping("/scenarios")
public class ScenariosController {
    @RequestMapping("/")
    public String display()
    {
        return "scenarios";
    }
}
