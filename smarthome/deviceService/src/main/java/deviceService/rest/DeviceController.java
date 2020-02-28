package deviceService.rest;

import deviceService.model.Device;
import deviceService.model.DeviceFactory;
import deviceService.model.Status;
import deviceService.model.Type;
import deviceService.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/devices/add/")
    public void addDevice(@RequestParam(name="alias")String alias,@RequestParam(name="userid") long userid, @RequestParam(name="type")String deviceType) {
        Device device = DeviceFactory.createDevice(Type.REST_DEVICE);
        device.setAliasName(alias);
        device.setUserId(userid);
        device.setToken("SOME TOKEN");
        device.setStatus(Status.ACTIVE);
        deviceService.addDevice(device);
    }

//    @PostMapping("/devices/?remove={id}")
//    public String removeDevice(@RequestParam(name = "id")Long id) {
//        deviceService.delete(id);
//        String resultString = "Device with id: "+id+" successfully deleted";
//        return resultString;
//    }



}
