package deviceService.rest;

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
        String url = "http://mqtt-service/test/on/" +
                "?ip=" + device.getIp() +
                "&port=" + device.getPort() +
                "&commandId=1"+
                "&device=" + device.getAliasName();
        return restTemplate.getForObject(url, String.class);
    }
}
