package ru.innopolis.zuul.mapper;

import dto.authservice.entities.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.zuul.model.Role;
import ru.innopolis.zuul.model.User;
import ru.innopolis.zuul.repository.RoleRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class UserMapper extends AbstractMapper<User, UserDto> {

    private ModelMapper mapper;
    private RoleRepository roleRepository;

    @Autowired
    public UserMapper(ModelMapper mapper, RoleRepository roleRepository) {
        super(User.class, UserDto.class);
        this.mapper = mapper;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(User.class, UserDto.class)
                .addMappings(e -> e.skip(UserDto::setRoles))
                .setPostConverter(toDtoConverter());

        mapper.createTypeMap(UserDto.class, User.class)
                .addMappings( e -> e.skip(User::setRoles))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(User source, UserDto destination) {
        destination.setRoles(getRoleNamesId(source));

    }

    @Override
    protected void mapSpecificFields(UserDto source, User destination) {
        destination.setRoles(getRoles(source));
    }

    private List<Role> getRoles(UserDto source) {
        List<String> roleIds = Arrays.asList(source.getRoles());
        List<Role> roles = new ArrayList<>();
        roleIds.stream().mapToLong((Long::parseLong)).forEach(e-> roles.add(roleRepository.findById(e).orElse(null)));
        return roles;
    }

    private String[] getRoleNamesId(User source) {
        String[] roleNames = new String[source.getRoles().size()];
        for (int i = 0; i < roleNames.length; i++) {
            roleNames[i] = String.valueOf(source.getRoles().get(i).getId());
        }
        return roleNames;
    }
}
