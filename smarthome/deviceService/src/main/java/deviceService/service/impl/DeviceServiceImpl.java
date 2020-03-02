package deviceService.service.impl;

import deviceService.model.Command;
import deviceService.model.Device;
import deviceService.model.Status;
import deviceService.model.Type;
import deviceService.repository.CommandRepository;
import deviceService.repository.DeviceRepository;
import deviceService.repository.TypeRepository;
import deviceService.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService {
    private DeviceRepository deviceRepository;
    private TypeRepository typeRepository;
    private CommandRepository commandRepository;

    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceRepository, TypeRepository typeRepository, CommandRepository commandRepository) {
//    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
        this.typeRepository = typeRepository;
        this.commandRepository = commandRepository;
    }

    @Override
    public Device addDevice(Device device) {
        Type deviceType = typeRepository.findByName("REST_DEVICE");
        List<Type> deviceTypes = new ArrayList<>();
        deviceTypes.add(deviceType);
        List<Command> commands = Arrays.asList(commandRepository.findByName("ON"), commandRepository.findByName("OFF"));
        device.setCommands(commands);
        device.setToken("NEW_TOKEN");
        device.setStatus(Status.ACTIVE);
        device.setTypes(deviceTypes);
        device.setCreated(LocalDateTime.now());
        device.setUpdated(LocalDateTime.now());
        Device registeredDevice = deviceRepository.save(device);
        log.info("IN addDevice - device {} successfully added", registeredDevice);
        return registeredDevice;
    }

    @Override
    public Device getDevice(Long id) {
        Device device = deviceRepository.findById(id).orElse(null);
        return device;
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
    public boolean update(Long id, String alias, List<String> typeNames) {
        Device device = deviceRepository.findById(id).orElse(null);
        if (device != null) {
            device.setAliasName(alias);
            List<Type> deviceTypes = new ArrayList<>();
            typeNames.forEach(e -> deviceTypes.add(typeRepository.findByName(e)));
            device.setTypes(deviceTypes);
            device.setUpdated(LocalDateTime.now());
            deviceRepository.save(device);
            log.info("In update: device with id {} successfully updated", id);
            return true;
        }
        log.warn("In update: there's no device with id {}", id);
        return false;
    }

    @Override
    public boolean delete(Long id) {
        Device device = deviceRepository.findById(id).orElse(null);
        if (device != null) {
            device.setStatus(Status.DELETED);
            device.setUpdated(LocalDateTime.now());
            deviceRepository.save(device);
            log.info("In delete: device with id {} successfully deleted", id);
            return true;
        }
        log.warn("In delete: device with id {} not found", id);
        return false;
    }

    @Override
    public boolean turnDeviceOn(Long id) {
        Device device =  deviceRepository.findById(id).orElse(null);
        if (device !=null) {

        }
        return  false;
    }
}
