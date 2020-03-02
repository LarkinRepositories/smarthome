package ru.innopolis.mqttService;


import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MqttController {
    private static final Logger logger = LoggerFactory.getLogger(MqttController.class);

    @RequestMapping("/MqttOn")
    public String mqttOn()  {
        mqttPub("192.168.1.1",1883,true,"DVES_5E1089");
        return "on device";
    }

    @RequestMapping("/test/on/")
    public String mqttOn(@RequestParam(name="ip")String ip,
                         @RequestParam(name="port")Integer port,
                         @RequestParam(name = "device")String deviceAlias)  {
//        mqttPub("192.168.1.1",1883,true,"DVES_5E1089");
        mqttPub(ip,port,true, deviceAlias);
        return "on device";
    }


    @RequestMapping("/MqttOff")
    public String mqttOff()  {
        mqttPub("192.168.1.1",1883,false,"DVES_5E1089");
        return "off device";
    }

    public void mqttPub(String host, int port, Boolean isOn, String device) {
        MqttCommon mqttCommon = new MqttCommon();
        String message;
        try {
            if (isOn) {
                message = "1";
            } else {
                message = "0";
            }

            String iotData = "cmnd/"+device+"/power";
            mqttCommon.publisher(host,port,iotData,message);
        } catch (MqttException e) {
            logger.error(String.valueOf(e));
        }
    }

    public void mqttSub(String host, int port, Boolean isOn, String device) {
        MqttCommon mqttCommon = new MqttCommon();
            String iotData = "iotData";
        try {
            mqttCommon.subscriber(host,port,iotData);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        //   mqttCommon.publisher(host,port,iotData,message);

    }

}
