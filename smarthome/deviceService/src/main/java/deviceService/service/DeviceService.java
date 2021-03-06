package deviceService.service;

import dto.deviceservice.entities.DeviceDto;

import java.util.List;

public interface DeviceService {
//    Device addDevice(Device device);
//    Device getDevice(Long id);
//    List<Device> getAll();
//    List<Device> findByUserId(Long userId);
//    boolean update(Long id, String alias, List<String> typeNames);
////    boolean update(Device device);
//    boolean delete(Long id);
//    boolean turnDeviceOn(Long id);
    DeviceDto addDevice(DeviceDto deviceDto);
    boolean update(DeviceDto deviceDto);
    boolean delete(DeviceDto deviceDto);
    DeviceDto getDevice(DeviceDto deviceDto);
    List<DeviceDto> findAllByUserId(Long userId);



}
