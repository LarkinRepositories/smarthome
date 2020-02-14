package deviceService.service.impl;

import deviceService.model.Device;
import deviceService.model.Status;
import deviceService.model.Type;
import deviceService.repository.DeviceRepository;
import deviceService.repository.TypeRepository;
import deviceService.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService {
    private DeviceRepository deviceRepository;
    private TypeRepository typeRepository;

    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceRepository, TypeRepository typeRepository) {
        this.deviceRepository = deviceRepository;
        this.typeRepository = typeRepository;
    }

    @Override
    public Device addDevice(Device device) {
        Type deviceType = typeRepository.findByName("DEVICE");
        List<Type> deviceTypes = new ArrayList<>();
        deviceTypes.add(deviceType);
        device.setStatus(Status.ACTIVE);
        Device registeredDevice = deviceRepository.save(device);
        log.info("IN addDevice - device {} successfully registered", registeredDevice);
        return registeredDevice;
    }

    @Override
    public List<Device> getAll() {
        List<Device> devices = deviceRepository.findAll();
        log.info("IN getAll - {} devices found", devices.size());
        return devices;
    }

    @Override
    public List<Device> findByUserId(Long userId) {
        List<Device> devices = deviceRepository.findAllDevicesByUserId(userId);
        log.info("In findByUserId - {} devices found with userid: {}", devices.size(), userId);
        return devices;
    }

    @Override
    public void delete(Long id) {
        deviceRepository.deleteById(id);
    }
}
