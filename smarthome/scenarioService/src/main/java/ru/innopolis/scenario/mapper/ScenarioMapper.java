package ru.innopolis.scenario.mapper;

import dto.scenarioservice.entities.ScenarioDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.scenario.model.Scenario;
import ru.innopolis.scenario.model.Type;
import ru.innopolis.scenario.repository.ScenarioRepository;
import ru.innopolis.scenario.repository.TypeRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScenarioMapper extends AbstractMapper<Scenario, ScenarioDto> {

    private ModelMapper mapper;
    private TypeRepository typeRepository;

    @Autowired
    public ScenarioMapper(ModelMapper mapper, TypeRepository typeRepository) {
        super(Scenario.class, ScenarioDto.class);
        this.mapper = mapper;
        this.typeRepository = typeRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Scenario.class, ScenarioDto.class)
                .addMappings(e -> e.skip(ScenarioDto::setTypes))
                .setPostConverter(toDtoConverter());

        mapper.createTypeMap(ScenarioDto.class, Scenario.class)
                .addMappings(e -> e.skip(Scenario::setTypes))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Scenario source, ScenarioDto destination) {
        super.mapSpecificFields(source, destination);
        destination.setTypes(getTypeNamesId(source));

    }

    @Override
    protected void mapSpecificFields(ScenarioDto source, Scenario destination) {
        destination.setTypes(getTypes(source));
    }

    private List<Type> getTypes(ScenarioDto source) {
        List<String> typeIds = Arrays.asList(source.getTypes());
        List<Type> types = new ArrayList<>();
        typeIds.stream().mapToLong(Long::parseLong).forEach(e -> types.add(typeRepository.findById(e).orElse(null)));
        return types;
    }


    private String[] getTypeNamesId(Scenario source) {
        String[] typeNames = new String[source.getTypes().size()];
        for (int i = 0; i < typeNames.length; i++) {
            typeNames[i] = String.valueOf(source.getTypes().get(i).getId());
        }
        return typeNames;
    }
}
