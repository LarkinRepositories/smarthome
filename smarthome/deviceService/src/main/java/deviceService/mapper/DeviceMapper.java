package deviceService.mapper;

import deviceService.dto.BaseEntityDto;
import deviceService.dto.DeviceDto;
import deviceService.model.BaseEntity;
import deviceService.model.Device;
import deviceService.repository.DeviceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DeviceMapper extends AbstractMapper<Device, DeviceDto> {

    @Autowired
    public DeviceMapper() {
        super(Device.class, DeviceDto.class);
    }
}
