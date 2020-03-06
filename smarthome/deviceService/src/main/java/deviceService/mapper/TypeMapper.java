package deviceService.mapper;


import deviceService.model.Type;
import deviceService.repository.TypeRepository;
import dto.deviceservice.entities.TypeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TypeMapper extends AbstractMapper<Type, TypeDto> {

    private ModelMapper mapper;
    private TypeRepository typeRepository;

    @Autowired
    public TypeMapper(ModelMapper mapper, TypeRepository repository) {
        super(Type.class, TypeDto.class);
        this.mapper = mapper;
        this.typeRepository = repository;
    }

//    @PostConstruct
//    public void setupMapper() {
//        mapper.createTypeMap(Type.class, TypeDto.class)
//        .addMappings(e -> e.skip(TypeDto::setDevicesIds)).setPostConverter(toDtoConverter());
//        mapper.createTypeMap(TypeDto.class, Type.class)
//                .addMappings(e -> e.skip(Type::setDevices)).setPostConverter(toEntityConverter());
//    }
//
//    @Override
//    protected void mapSpecificFields(Type source, TypeDto destination) {
//        destination.setDevicesIds(getIds(source));
//    }
//
//
//    @Override
//    protected void mapSpecificFields(TypeDto source, Type destination) {
//        destination.setDevices(Objects.requireNonNull(typeRepository.findById(source.getId()).orElse(null)).getDevices());
//    }
//
//    private List<Long> getIds(Type source) {
//        List<Long> idList = new ArrayList<>();
//        source.getDevices().forEach(e -> idList.add(e.getId()));
//        return idList;
//    }



}
