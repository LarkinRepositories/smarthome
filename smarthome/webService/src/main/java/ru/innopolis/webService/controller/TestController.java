package ru.innopolis.webService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.web.client.RestTemplate;
import ru.innopolis.webService.pojo.User;



@RestController
@RequestMapping("/")
public class TestController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public ModelAndView index(){
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/saveProfile", method = RequestMethod.POST)
    public ModelAndView submit(@Valid @ModelAttribute("user")User user,
                         BindingResult result, ModelMap model) {
        model.addAttribute("name", user.getName());
        model.addAttribute("address", user.getAddress());
        model.addAttribute("phone", user.getPhone());
        model.addAttribute("telegram", user.getTelegram());
        StringBuilder url = new StringBuilder("http://sms-service/sendSMS?to=").append(user.getPhone()).append("&message=")
                .append("Profile was changed");
        ResponseEntity response = restTemplate.exchange(url.toString(), HttpMethod.GET, null, Object.class);
        return new ModelAndView("main");
    }

    @PostMapping("/auth-form")
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView  sayHello(@RequestParam("name") String name, Model model,
                                  HttpServletRequest request,
                                  HttpServletResponse response){
        model.addAttribute("name", name);
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
        ModelAndView mav = new ModelAndView("profile", "user", new User());
        return mav;
    }

    @GetMapping(value = "/exit")
    public ModelAndView handleRequestExit(HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("exit");
        return mav;
    }
}