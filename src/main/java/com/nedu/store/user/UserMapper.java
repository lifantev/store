package com.nedu.store.user;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserMapper(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(UserEntity.class, UserDto.class)
                .addMappings(m -> m.skip(UserDto::setPassword))
                .setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(UserDto.class, UserEntity.class)
                .addMappings(m -> m.skip(UserEntity::setPassword))
                .addMappings(m -> m.skip(UserEntity::setBasket))
                .setPostConverter(toEntityConverter());
    }

    public UserDto toDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }

    public UserEntity toEntity(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }

    public Converter<UserEntity, UserDto> toDtoConverter() {
        return context -> {
            UserEntity source = context.getSource();
            UserDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public Converter<UserDto, UserEntity> toEntityConverter() {
        return context -> {
            UserDto source = context.getSource();
            UserEntity destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    private void mapSpecificFields(UserEntity source, UserDto destination) {
            destination.setPassword(source.getPassword());
            //TODO decryption
    }

    private void mapSpecificFields(UserDto source, UserEntity destination) {
        Optional<UserEntity> saved = userRepository.findByLogin(source.getLogin());

        if (saved.isPresent()) {
            destination.setPassword(saved.get().getPassword());
            destination.setBasket(saved.get().getBasket());
        } else {
            destination.setPassword(source.getPassword());
            //TODO encryption, basket?
        }
    }
}
