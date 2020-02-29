package deviceService.service;

import deviceService.model.Device;

import java.util.List;

public interface DeviceService {
    Device addDevice(Device device);
    List<Device> getAll();
    List<Device> findByUserId(Long userId);
    boolean update(Long id, String alias, List<String> typeNames);
    boolean delete(Long id);

}
