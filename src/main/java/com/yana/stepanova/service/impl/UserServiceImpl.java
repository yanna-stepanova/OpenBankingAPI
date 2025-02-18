package com.yana.stepanova.service.impl;

import com.yana.stepanova.dto.user.UserRegistrationRequestDto;
import com.yana.stepanova.dto.user.UserResponseDto;
import com.yana.stepanova.exception.EntityNotFoundCustomException;
import com.yana.stepanova.exception.RegistrationException;
import com.yana.stepanova.mapper.UserMapper;
import com.yana.stepanova.model.Role;
import com.yana.stepanova.model.User;
import com.yana.stepanova.repository.user.RoleRepository;
import com.yana.stepanova.repository.user.UserRepository;
import com.yana.stepanova.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepo.existsByEmail(requestDto.email())) {
            throw new RegistrationException("This user can't be registered");
        }
        User user = userMapper.toModel(requestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole(getRoleByName(Role.RoleName.CLIENT.getRoleName()));
        }
        return userMapper.toResponseDto(userRepo.save(user));
    }

    private Role getRoleByName(String roleName) {
        return roleRepo.findByName(Role.RoleName.valueOf(roleName.toUpperCase()))
                .orElseThrow(() -> new EntityNotFoundCustomException(
                        String.format("Can't find %s in table roles", roleName)));
    }
}
