package deviceService.rest;


import deviceService.model.Device;
import deviceService.model.Status;
import deviceService.service.DeviceService;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/devices/get/")
    @LoadBalanced
    public List<Device> getDevices(@RequestParam(name = "userId") Long userId) {
        List<Device> devices = deviceService.findByUserId(userId);
        return devices;
    }

    @PostMapping("/devices/add/")
    public void addDevice(@RequestParam(name="alias")String alias,
                          @RequestParam(name="ip")String ip,
                          @RequestParam(name="port")Integer port,
                          @RequestParam(name="userId") Long userId) {
        Device device = new Device();
        device.setAliasName(alias);
        device.setIp(ip);
        device.setPort(port);
        device.setUserId(userId);
        deviceService.addDevice(device);
    }

//    @PostMapping("/devices/add/")
//    public void addDevice(@RequestBody DeviceDto deviceDto) {
//
//    }


    @PostMapping("/devices/update/")
    public void updateDevice(@RequestParam(name = "id")Long id,
                             @RequestParam(name = "alias")String alias,
                             @RequestParam(name = "types")List<String> types) {
        deviceService.update(id, alias, types);
    }

    @PostMapping("/devices/delete/")
    public String removeDevice(@RequestParam(name = "id")Long id) {
        deviceService.delete(id);
        String resultString = "Device with id: "+id+" successfully deleted";
        return resultString;
    }



}
