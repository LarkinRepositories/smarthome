package deviceService.rest;

import deviceService.model.Command;
import deviceService.model.Device;
import deviceService.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/devices/on/")
    public String turnDeviceOn(@RequestParam(name ="id")Long deviceId) {
        Device device = deviceService.getDevice(deviceId);
        Long commandId = device.getCommands().get(0).getCommandId();
        String url = "http://mqtt-service/test/on/" +
                "?ip=" + device.getIp() +
                "&port=" + device.getPort() +
                "&commandId="+ commandId +
                "&device=" + device.getAliasName();
        return restTemplate.getForObject(url, String.class);
    }

    @PostMapping("/devices/off/")
    public String turnDeviceOff(@RequestParam(name ="id")Long deviceId) {
        Device device = deviceService.getDevice(deviceId);
        Long commandId = device.getCommands().get(1).getCommandId();
        String url = "http://mqtt-service/test/off/" +
                "?ip=" + device.getIp() +
                "&port=" + device.getPort() +
                "&commandId="+ commandId +
                "&device=" + device.getAliasName();
        return restTemplate.getForObject(url, String.class);

    }
}
