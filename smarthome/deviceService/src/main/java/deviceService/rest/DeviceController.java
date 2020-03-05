package deviceService.rest;


import deviceService.service.DeviceService;
import dto.deviceservice.entities.DeviceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
@Slf4j
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/devices/on/")
    public String turnDeviceOn(@RequestParam(name ="id")Long deviceId) {
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setId(deviceId);
        DeviceDto deviceDto1 = deviceService.getDevice(deviceDto);
        long commandId = Long.parseLong(deviceDto1.getCommands()[0]);
        String url = "";
        if (deviceDto1.getTypes().length == 1 && deviceDto1.getTypes()[0].equals("1")) {
            url="http://rest-service-out/test/do/" +
                    "?ip=" + deviceDto1.getIp() +
                    "&commandId=" + deviceDto1.getCommands()[0];
                    //Long.valueOf(deviceDto1.getCommands()[0]);
        } else {
            url = "http://mqtt-service/test/on/" +
                    "?ip=" + deviceDto1.getIp() +
                    "&port=" + deviceDto1.getPort() +
                    "&commandId=" + commandId +
                    "&device=" + deviceDto1.getAliasName();
        }
        log.debug("IN ON BEFORE deviceDTO: {}",deviceDto1.toString());
        deviceDto1.setOperating(true);
        log.debug("IN ON AFTER deviceDTO: {}", deviceDto1.toString());
        deviceService.update(deviceDto1);
        return restTemplate.getForObject(url, String.class);
    }

    @RequestMapping("/devices/off/")
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
        log.warn(deviceDto1.toString());
        deviceDto1.setOperating(false);
        log.warn(deviceDto1.toString());
        deviceService.update(deviceDto1);
        return restTemplate.getForObject(url, String.class);

    }

    @GetMapping("/devices/device/isOp/")
    public Boolean isDeviceOperating(@RequestParam(name="id")Long deviceId) {
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setId(deviceId);
        DeviceDto deviceDto1 = deviceService.getDevice(deviceDto);
        return deviceDto1.getOperating();
    }
}
