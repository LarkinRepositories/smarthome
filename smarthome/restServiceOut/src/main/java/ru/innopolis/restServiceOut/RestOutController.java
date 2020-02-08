package ru.innopolis.restServiceOut;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class RestOutController {

    public static final Logger logger = LoggerFactory.getLogger(RestOutController.class);

    @RequestMapping("/restOn")
    public String restOn()  {
        return getRequest("192.168.1.93",true);
    }


    @RequestMapping("/restOff")
    public String off()  {
        return getRequest("192.168.1.93",false);
    }

    private String getRequest(String ip,boolean isOn) {
        String switcher;
        if (isOn) {
            switcher = "On";
        } else {
            switcher = "Off";
        }

        final String uri = "http://"+ip+"/cm?cmnd=Power%20"+switcher+"";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri,String.class);
    }

}
