package deviceService.mapper;

import deviceService.model.Command;
import deviceService.model.Device;
import deviceService.model.Type;
import deviceService.repository.CommandRepository;
import deviceService.repository.TypeRepository;
import dto.deviceservice.entities.DeviceDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DeviceMapper extends AbstractMapper<Device, DeviceDto> {
    private ModelMapper mapper;
    private TypeRepository typeRepository;
    private CommandRepository commandRepository;

    @Autowired
    public DeviceMapper(ModelMapper mapper, TypeRepository typeRepository, CommandRepository commandRepository) {
        super(Device.class, DeviceDto.class);
        this.mapper = mapper;
        this.typeRepository = typeRepository;
        this.commandRepository = commandRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Device.class, DeviceDto.class)
                .addMappings(e-> e.skip(DeviceDto::setTypes))
                .addMappings(e -> e.skip(DeviceDto::setCommands))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(DeviceDto.class, Device.class)
                .addMappings(e -> e.skip(Device::setTypes))
//                .addMappings(e -> e.skip(Device::setCommands))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Device source, DeviceDto destination) {
        destination.setTypes(getTypeNames(source));
        destination.setCommands(getCommandIds(source));
    }

    @Override
    protected void mapSpecificFields(DeviceDto source, Device destination) {
        destination.setTypes(getTypes(source));
//        destination.setCommands(getCommands(source));
    }

    private String[] getTypeNames(Device source) {
//        List<String> typeIdList = new ArrayList<>();
//        source.getTypes().forEach(type -> String.valueOf(typeRepository.findById(type.getId()).orElse(null).getId()));
//        return (String[]) typeIdList.toArray();
        String[] strings = new String[source.getTypes().size()];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = String.valueOf(source.getTypes().get(i).getId());

        }
        return strings;
    }

    private List<Type> getTypes(DeviceDto source) {
        List<String> typeNames = Arrays.asList(source.getTypes());
        List<Long> longs = new ArrayList<>();
        typeNames.forEach(e-> longs.add(Long.valueOf(e)));
        List<Type> types = typeRepository.findAllById(longs);
//        List<Long> ids = new ArrayList<>();
//
////        Arrays.asList(source.getTypes()).forEach(e-> types.add(typeRepository.findByName(e)));
        return types;
    }

//    private String[] getCommandIds(Device source) {
//        String[] strings = new String[source.getCommands().size()];
//        for (int i = 0; i < strings.length; i++) {
//            strings[i] = String.valueOf(commandRepository.findById(source.getCommands().get(i).getId()));
//        }
//        return strings;
//    }
//
//    private List<Command> getCommands(DeviceDto source) {
//        List<String> commandIds = Arrays.asList(source.getCommands());
//        List<Long> longs = new ArrayList<>();
//        commandIds.forEach(e -> longs.add(Long.valueOf(e)));
//        List<Command> commands = commandRepository.findAllById(longs);
//        return commands;
//    }

    private String[] getCommandIds(Device source) {
        List<List<Command>> list  = source.getTypes().stream().map(Type::getCommands).collect(Collectors.toList());
        List<Command> commands = new ArrayList<>();
        for (List<Command> commandList : list) {
            commands.addAll(commandList);
        }
        List<Command> list1 = commands.stream().distinct().collect(Collectors.toList());
        String[] strings = new String[list1.size()];
        for (int i = 0; i < strings.length; i++) {
             strings[i] = String.valueOf(commandRepository.findById(list1.get(i).getId()).orElse(null).getCommandId());

        }
        return strings;
    }
}
