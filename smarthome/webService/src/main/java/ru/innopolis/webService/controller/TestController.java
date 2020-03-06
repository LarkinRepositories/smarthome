package ru.innopolis.webService.controller;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.webService.device_dto.DeviceDto;
import ru.innopolis.webService.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collections;


@RestController
@RequestMapping("/")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public ModelAndView index() {
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
        return new ModelAndView("main1");
    }

    @GetMapping(value = "/web/main")
    public ModelAndView handleRequestIndex(HttpServletRequest request,
                                             HttpServletResponse response, ModelMap modelMap) throws Exception {
        ModelAndView mav = new ModelAndView("main1");
        String qwerty = restHelper("http://127.0.0.1:10100/MqttOff",HttpMethod.GET);
        modelMap.addAttribute("dw",qwerty);


        return mav;
    }

    @GetMapping(value = "/web/scenarios")
    public ModelAndView handleRequestScenarios(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
//        Collection<Scenario> scenarios = получитьСценарии;
//        request.setAttribute("scenarios", scenarios);
//        request.getRequestDispatcher("/scenarios.jsp").forward(request, response);
        ModelAndView mav = new ModelAndView("scenarios1");
        return mav;
    }

    @GetMapping(value = "/web/devices")
    public ModelAndView handleRequestDevices(HttpServletRequest request,
                                      HttpServletResponse response,ModelMap modelMap) throws Exception {
        ModelAndView mav = new ModelAndView("devices1");
        //  String stringPosts = restTemplate.getForObject("http://device-service/devices/", String.class);
        //  logger.info(stringPosts);

        String deviceJson = restHelper("http://localhost:8762/api/device/devices/device/get/1",HttpMethod.GET);
        Gson gson = new Gson();
        DeviceDto deviceDto = gson.fromJson(deviceJson, DeviceDto.class);
        modelMap.addAttribute("DevId",deviceDto.getId());
        modelMap.addAttribute("DevName",deviceDto.getAliasName());
        modelMap.addAttribute("DevIp",deviceDto.getIp());
        modelMap.addAttribute("DevPort",deviceDto.getPort());
        modelMap.addAttribute("DevStatus",deviceDto.getStatus());
        modelMap.addAttribute("DevState",deviceDto.getOperating());
        modelMap.addAttribute("DevUser",deviceDto.getUserId());
        modelMap.addAttribute("DevType",deviceDto.getTypes()[0]);
        return mav;
    }

    @GetMapping(value = "/web/profile")
    public ModelAndView handleRequestProfile(HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("profile1", "user", new User());
        return mav;
    }

    @GetMapping(value = "/web/exit")
    public ModelAndView handleRequestExit(HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("exit");
        return mav;
    }

    private String restHelper(String url , HttpMethod httpMethod){
         RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        return restTemplate.exchange(url, httpMethod, entity, String.class).getBody();
    }

}