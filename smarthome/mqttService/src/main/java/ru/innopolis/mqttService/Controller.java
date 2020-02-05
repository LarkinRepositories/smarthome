package ru.innopolis.mqttService;


import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {
    public static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @RequestMapping("/on")
    public String on()  {
        mqtt(true,"DVES_5E1089");
        return "on device";
    }

    @RequestMapping("/off")
    public String off()  {
        mqtt(false,"DVES_5E1089");
        return "off device";
    }

    public void mqtt(Boolean isOn,String device) {
        MqttCommon mqttCommon = new MqttCommon();
        String message = "";
        try {
            if (isOn == true) {
                message = "1";
            } else {
                message = "0";
            }

            String iotData = "cmnd/"+device+"/power";
            mqttCommon.publisher("192.168.1.1",1883,iotData,message);
        } catch (MqttException e) {
            logger.error(String.valueOf(e));
        }
    }

}
