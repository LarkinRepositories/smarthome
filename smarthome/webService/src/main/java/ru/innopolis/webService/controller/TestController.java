package ru.innopolis.webService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Map;


@RestController
@RequestMapping("/")
public class TestController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public ModelAndView index(){
        return new ModelAndView("login");
    }
//    @GetMapping("/login")
//    public String afterAuth(@RequestParam("name") String name){
//        return "index";
//    }

    @PostMapping("/auth-form")
    public ModelAndView  sayHello(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        //String uri = restTemplate.getForObject("http://auth-service/api/auth/", String.class);
                //    org.springframework.web.client.HttpClientErrorException$Unauthorized: 401 :
                //    [{"timestamp":"2020-02-23T16:46:39.212+0000","status":401,"error":"Unauthorized",
                //    "m*essage":"No message available","path":"/api/auth/"}]
        return new ModelAndView("main");
    }

    @GetMapping(value = "/auth-form")
    public ModelAndView handleRequestIndex(HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("main");
        return mav;
    }

    @GetMapping(value = "/scenarios")
    public ModelAndView handleRequestScenarios(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
//        Collection<Scenario> scenarios = получитьСценарии;
//        request.setAttribute("scenarios", scenarios);
//        request.getRequestDispatcher("/scenarios.jsp").forward(request, response);
        ModelAndView mav = new ModelAndView("scenarios");
        return mav;
    }

    @GetMapping(value = "/devices")
    public ModelAndView handleRequestDevices(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("devices");
        return mav;
    }

    @GetMapping(value = "/profile")
    public ModelAndView handleRequestProfile(HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("profile");
        return mav;
    }

    @GetMapping(value = "/exit")
    public ModelAndView handleRequestExit(HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("exit");
        return mav;
    }

}