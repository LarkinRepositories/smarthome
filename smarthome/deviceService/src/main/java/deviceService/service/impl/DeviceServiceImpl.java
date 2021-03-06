package deviceService.service.impl;


import deviceService.mapper.DeviceMapper;
import deviceService.model.Device;
import deviceService.repository.DeviceRepository;
import deviceService.service.DeviceService;
import dto.base.Status;
import dto.deviceservice.entities.DeviceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService {
    private DeviceRepository deviceRepository;
    private DeviceMapper mapper;

    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceRepository, DeviceMapper mapper) {
        this.deviceRepository = deviceRepository;
        this.mapper = mapper;
    }

    @Override
    public DeviceDto addDevice(DeviceDto deviceDto) {
        deviceDto.setCreated(LocalDateTime.now());
        deviceDto.setUpdated(LocalDateTime.now());
        deviceDto.setStatus(Status.ACTIVE);
        return mapper.toDto(deviceRepository.save(mapper.toEntity(deviceDto)));
    }

    @Override
    public List<DeviceDto> findAllByUserId(Long userId) {
        List<Device> devices = deviceRepository.findAllDevicesByUserId(userId);
        List<DeviceDto> deviceDtos = new ArrayList<>();
        devices.forEach(e -> deviceDtos.add(mapper.toDto(e)));
        return deviceDtos;
    }

    @Override
    public boolean update(DeviceDto deviceDto) {
       Device device = deviceRepository.findById(deviceDto.getId()).orElse(null);
         if (device != null) {
           deviceDto.setCreated(device.getCreated());
           deviceDto.setUpdated(LocalDateTime.now());
           deviceRepository.save(mapper.toEntity(deviceDto));
           return true;
         }
       return false;
    }

    @Override
    public boolean delete(DeviceDto deviceDto) {
        Device device = deviceRepository.findById(deviceDto.getId()).orElse(null);
        if (device != null)  {
            deviceDto.setStatus(Status.DELETED);
            deviceDto.setCreated(device.getCreated());
            deviceDto.setUpdated(LocalDateTime.now());
            deviceRepository.save(mapper.toEntity(deviceDto));
            return true;
        }
        return false;
    }

    @Override
    public DeviceDto getDevice(DeviceDto deviceDto) {
        return  mapper.toDto(deviceRepository.findById(deviceDto.getId()).orElse(null));
    }

    //    private TypeRepository typeRepository;
//    private CommandRepository commandRepository;
//    private DeviceMapper mapper;
//
//    @Autowired
//    public DeviceServiceImpl(DeviceRepository deviceRepository, TypeRepository typeRepository, CommandRepository commandRepository, DeviceMapper mapper) {
////    public DeviceServiceImpl(DeviceRepository deviceRepository) {
//        this.deviceRepository = deviceRepository;
//        this.typeRepository = typeRepository;
//        this.commandRepository = commandRepository;
//        this.mapper = mapper;
//    }
//
//    @Override
//    public Device addDevice(Device device) {
//        Type deviceType = typeRepository.findByName("REST_DEVICE");
//        List<Type> deviceTypes = new ArrayList<>();
//        deviceTypes.add(deviceType);
//        List<Command> commands = Arrays.asList(commandRepository.findByName("ON"), commandRepository.findByName("OFF"));
//        device.setCommands(commands);
//        device.setToken("NEW_TOKEN");
//        device.setStatus(Status.ACTIVE);
//        device.setTypes(deviceTypes);
//        device.setCreated(LocalDateTime.now());
//        device.setUpdated(LocalDateTime.now());
//        Device registeredDevice = deviceRepository.save(device);
//        log.info("IN addDevice - device {} successfully added", registeredDevice);
//        return registeredDevice;
//    }
//
//    @Override
//    public Device getDevice(Long id) {
//        Device device = deviceRepository.findById(id).orElse(null);
//        return device;
//    }
//
//    @Override
//    public List<Device> getAll() {
//        List<Device> devices = deviceRepository.findAll();
//        log.info("IN getAll - {} devices found", devices.size());
//        return devices;
//    }
//
//    @Override
//    public List<Device> findByUserId(Long userId) {
//        List<Device> devices = deviceRepository.findAllDevicesByUserId(userId);
//        log.info("In findByUserId - {} devices found with userid: {}", devices.size(), userId);
//        return devices;
//    }
//
//    @Override
//    public boolean update(Long id, String alias, List<String> typeNames) {
//        Device device = deviceRepository.findById(id).orElse(null);
//        if (device != null) {
//            device.setAliasName(alias);
//            List<Type> deviceTypes = new ArrayList<>();
//            typeNames.forEach(e -> deviceTypes.add(typeRepository.findByName(e)));
//            device.setTypes(deviceTypes);
//            device.setUpdated(LocalDateTime.now());
//            deviceRepository.save(device);
//            log.info("In update: device with id {} successfully updated", id);
//            return true;
//        }
//        log.warn("In update: there's no device with id {}", id);
//        return false;
//    }
//
//    @Override
//    public boolean delete(Long id) {
//        Device device = deviceRepository.findById(id).orElse(null);
//        if (device != null) {
//            device.setStatus(Status.DELETED);
//            device.setUpdated(LocalDateTime.now());
//            deviceRepository.save(device);
//            log.info("In delete: device with id {} successfully deleted", id);
//            return true;
//        }
//        log.warn("In delete: device with id {} not found", id);
//        return false;
//    }
//
//    @Override
//    public boolean turnDeviceOn(Long id) {
//        Device device =  deviceRepository.findById(id).orElse(null);
//        if (device !=null) {
//
//        }
//        return  false;
//    }


}
