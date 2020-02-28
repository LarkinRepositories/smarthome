package deviceService.model;

public class DeviceFactory {

    public static Device createDevice(Type type) {
        Device device = null;
        switch(type) {
            case MQTT_DEVICE: device =  new MqttDevice(); break;
            case REST_DEVICE: device = new RestDevice(); break;
            default: throw new  IllegalArgumentException("Wrong device type");
        }
        return device;
    }
}
