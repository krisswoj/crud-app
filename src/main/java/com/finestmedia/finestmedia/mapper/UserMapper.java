package com.finestmedia.finestmedia.mapper;

import com.finestmedia.finestmedia.domain.model.dto.UserCreationDto;
import com.finestmedia.finestmedia.domain.model.dto.UserDto;
import com.finestmedia.finestmedia.domain.model.entity.UserEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserEntityFromUserDto(UserDto userDto, @MappingTarget UserEntity userEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserEntityFromUserCreationDto(UserCreationDto userCreationDto, @MappingTarget UserEntity userEntity);
}
