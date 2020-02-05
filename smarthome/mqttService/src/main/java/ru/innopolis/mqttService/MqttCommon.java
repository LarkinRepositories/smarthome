package ru.innopolis.mqttService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;


@Service
public class MqttCommon {

    private static Logger logger = LogManager.getLogger(SimpleMqttCallBack.class);

    public void subscriber(String host, int port) throws MqttException {
        MqttClient client=new MqttClient("tcp://"+host+":"+String.valueOf(port)+"", MqttClient.generateClientId());
        client.setCallback( new SimpleMqttCallBack() );
        client.connect();

        client.subscribe("iot_data");
    }

    public void publisher(String host, int port,String iotData,String messageString) throws MqttException {
        //System.out.println("== START PUBLISHER ==");
        MqttClient client = new MqttClient("tcp://"+host+":"+port+"", MqttClient.generateClientId());
        client.connect();
        MqttMessage message = new MqttMessage();
        message.setPayload(messageString.getBytes());
        client.publish(iotData, message);
        logger.info("\tMessage '"+ messageString +" to "+iotData+"");
        client.disconnect();
        //System.out.println("== END PUBLISHER ==");
    }

}
