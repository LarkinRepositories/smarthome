package ru.innopolis.webService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TestController {

    @GetMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }
//    @GetMapping("/")
//    public String index(){
//        return "index";
//    }

    @PostMapping("/hello")
    public ModelAndView  sayHello(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return new ModelAndView("hello ");
    }
//    @PostMapping("/hello")
//    public String sayHello(@RequestParam("name") String name, Model model){
//        model.addAttribute("name", name);
//        return "hello ";
//    }


}