package deviceService.mapper;

import deviceService.dto.CommandDto;
import deviceService.model.Command;
import deviceService.model.Type;
import deviceService.repository.CommandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandMapper extends AbstractMapper<Command, CommandDto> {

    private ModelMapper mapper;
    private CommandRepository repository;

    @Autowired
    public CommandMapper(ModelMapper mapper, CommandRepository repository) {
        super(Command.class, CommandDto.class);
        this.mapper = mapper;
        this.repository = repository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Command.class, CommandDto.class)
                .addMappings(e-> e.skip(CommandDto::setDevicesIds))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(CommandDto.class, Command.class)
                .addMappings(e -> e.skip(Command::setDevices)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Command source, CommandDto destination) {
        destination.setDevicesIds(getIds(source));
    }

    @Override
    protected void mapSpecificFields(CommandDto source, Command destination) {
        destination.
                setDevices(Objects.requireNonNull(repository.findById(source.getId()).orElse(null))
                        .getDevices());
    }

    private List<Long> getIds(Command source) {
        List<Long> idList = new ArrayList<>();
        source.getDevices().forEach(e -> idList.add(e.getId()));
        return idList;
    }

}
