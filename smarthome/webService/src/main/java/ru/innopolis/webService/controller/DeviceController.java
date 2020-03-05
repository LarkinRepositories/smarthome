package ru.innopolis.webService.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Класс DeviceController
 * <p>
 * 01.03.2020
 *
 * @author Александр Коваленко
 */
@Controller
public class DeviceController {
    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);
    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/devices/add")
    public ModelAndView addRequestDevicesView(HttpServletRequest request,
                                              HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("devices-add");
        return mav;
    }

    @PostMapping(value = "/devices/add")
    public ModelAndView addDevice(HttpServletRequest request,
                                  HttpServletResponse response, @RequestBody MultiValueMap<String, String> formData) throws Exception {
        String alias = formData.toSingleValueMap().get("alias");
        //logger.info(alias);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("alias", alias);

        //to-do получить user id из сессии
        map.add("userId", "1");

        HttpEntity<MultiValueMap<String, String>> requestDeviceService = new HttpEntity<>(map, headers);
        ResponseEntity<String> responseDeviceService = restTemplate.postForEntity(
                "http://device-service/devices/add/", requestDeviceService, String.class);

        logger.info(String.valueOf(responseDeviceService.getStatusCode()));
        logger.info(String.valueOf(responseDeviceService));

        ModelAndView mav = new ModelAndView("devices-add");
        if (responseDeviceService.getStatusCode().equals(HttpStatus.OK)) {
            String allIsOk = "Устройство " + alias + " добавлено!";
            logger.info("Успешно добавлено устройство");
            mav.addObject("success", allIsOk);
        }


        return mav;
    }


}
