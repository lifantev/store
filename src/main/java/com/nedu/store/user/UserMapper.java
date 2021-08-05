package com.nedu.store.user;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserMapper(
            ModelMapper modelMapper,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(UserEntity.class, UserDto.class)
                .addMappings(m -> m.skip(UserDto::setPassword));

        modelMapper.createTypeMap(UserDto.class, UserEntity.class)
                .addMappings(m -> m.skip(UserEntity::setPassword));
    }

    public UserDto toDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }

    public UserEntity toEntity(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }
}
