package deviceService.service;

import deviceService.model.Device;

import java.util.List;

public interface DeviceService {
    Device addDevice(Device device);
    List<Device> getAll();
    List<Device> findByUserId(Long userId);
    void delete(Long id);

}
