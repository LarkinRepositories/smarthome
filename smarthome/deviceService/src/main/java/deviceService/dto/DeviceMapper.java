package deviceService.dto;

import deviceService.model.BaseEntity;
import deviceService.model.Device;
import deviceService.model.Type;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class DeviceMapper {
//    @Autowired
//    private ModelMapper mapper;
//
//    public Device toEntity(DeviceDto dto) {
//        return Objects.isNull(dto) ? null : mapper.map(dto, Device.class);
//    }
//
//    public DeviceDto toDto(Device entity) {
//        return Objects.isNull(entity) ? null : mapper.map(entity, DeviceDto.class);
//    }
//
////    Converter<DeviceDto, Device> toEntityConverter() {
////        return MappingContext::getDestination;
////    }
//
//    public Converter<DeviceDto, Device> toEntityConverter() {
//        return context -> {
//          DeviceDto source = context.getSource();
//          Device destination = context.getDestination();
//          mapSpecifiedFields(source , destination);
//          return context.getDestination();
//        };
//    }
//
//
//    public void mapSpecifiedFields(Type source, TypeDto destination) {
//        destination.setDevicesIds(source.getDevices().forEach(BaseEntity::getId).
//    }
//
//
//    @PostConstruct
//    public void setupMapper() {
//        mapper.createTypeMap(Type.class, TypeDto.class)
//                .addMapping(m -> m.skip(TypeDto::setDevicesIds)).setPostConverter(toDto())
//    }
}
