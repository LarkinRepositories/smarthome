package deviceService.rest;

import deviceService.model.Device;
import deviceService.repository.DeviceRepository;
import deviceService.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping("/hello")
    @LoadBalanced
    public String getHello() {
        return "Hello from device service, you are authorized";
    }

//    @GetMapping("/devices/")
//    public String getAllDevices() {
//        return deviceService.getAll().toString();
//    }

//    @GetMapping("/devices")
//    public List<Device> getAllDevices() {
//       /List<Device> devices = Arrays.asList(new Device(1, "TOKEN1", Collections.emptyList()), new Device(1, "TOKEN2", Collections.emptyList()));
//       return devices;
//    }

    @GetMapping("/devices/")
    @LoadBalanced
    public List<Device> getDevices(@RequestParam(name = "userId") Long userId) {
        List<Device> devices = deviceService.findByUserId(userId);
        return devices;
    }

//    @PostMapping("/devices/?remove={id}")
//    public String removeDevice(@RequestParam(name = "id")Long id) {
//        deviceService.delete(id);
//        String resultString = "Device with id: "+id+" successfully deleted";
//        return resultString;
//    }



}
