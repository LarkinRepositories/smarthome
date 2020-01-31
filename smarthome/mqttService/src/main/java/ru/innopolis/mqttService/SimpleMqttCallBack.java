package ru.innopolis.mqttService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SimpleMqttCallBack implements MqttCallback {

    private static Logger logger = LogManager.getLogger(SimpleMqttCallBack.class);

    public void connectionLost(Throwable throwable) {
        logger.warn("Connection to MQTT broker lost!");
    }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        logger.info("Message received:\t"+ new String(mqttMessage.getPayload()));
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }

}
