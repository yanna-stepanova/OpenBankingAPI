package com.yana.stepanova.service;

import com.yana.stepanova.dto.user.UserRegistrationRequestDto;
import com.yana.stepanova.dto.user.UserResponseDto;
import com.yana.stepanova.exception.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;
}
