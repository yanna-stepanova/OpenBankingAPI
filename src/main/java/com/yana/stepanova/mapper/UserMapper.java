package com.yana.stepanova.mapper;

import com.yana.stepanova.config.MapperConfig;
import com.yana.stepanova.dto.user.UserRegistrationRequestDto;
import com.yana.stepanova.dto.user.UserResponseDto;
import com.yana.stepanova.model.Role;
import com.yana.stepanova.model.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    User toModel(UserRegistrationRequestDto requestDto);

    @Mapping(target = "roleName", ignore = true)
    UserResponseDto toResponseDto(User user);

    @AfterMapping
    default void setRole(@MappingTarget User user, UserRegistrationRequestDto requestDto) {
        Role role = new Role();
        Role.RoleName roleName = Role.RoleName.CLIENT;
        role.setId((long) roleName.ordinal() + 1);
        role.setName(roleName);
        user.setRole(role);
    }

    @AfterMapping
    default void setRoleName(@MappingTarget UserResponseDto userDto, User user) {
        userDto.setRoleName(user.getRole().getAuthority());
    }
}
