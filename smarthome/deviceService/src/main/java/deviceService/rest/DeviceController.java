package deviceService.rest;

import deviceService.dto.DeviceDto;
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
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setId(deviceId);
        DeviceDto deviceDto1 = deviceService.getDevice(deviceDto);
        long commandId = Long.parseLong(deviceDto1.getCommands()[0]);
        String url = "http://mqtt-service/test/on/" +
                "?ip=" + deviceDto1.getIp() +
                "&port=" + deviceDto1.getPort() +
                "&commandId="+ commandId +
                "&device=" + deviceDto1.getAliasName();
        return restTemplate.getForObject(url, String.class);
    }

    @PostMapping("/devices/off/")
    public String turnDeviceOff(@RequestParam(name ="id")Long deviceId) {
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setId(deviceId);
        DeviceDto deviceDto1 = deviceService.getDevice(deviceDto);
        long commandId = Long.parseLong(deviceDto1.getCommands()[1]);
        String url = "http://mqtt-service/test/off/" +
                "?ip=" + deviceDto1.getIp() +
                "&port=" + deviceDto1.getPort() +
                "&commandId="+ commandId +
                "&device=" + deviceDto.getAliasName();
        return restTemplate.getForObject(url, String.class);

    }
}
