package ru.innopolis.webService.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TestController {

    @GetMapping("/")
    public ModelAndView index(){
        return new ModelAndView("login");
    }
//    @GetMapping("/")
//    public String index(){
//        return "index";
//    }

    @PostMapping("/main")
    public ModelAndView  sayHello(@RequestParam("name") String name, Model model){
     //   model.addAllAttributes(new HashMap<>())
        model.addAttribute("name", name);
        return new ModelAndView("main");
    }
//    @PostMapping("/hello")
//    public String sayHello(@RequestParam("name") String name, Model model){
//        model.addAttribute("name", name);
//        return "hello ";
//    }


}