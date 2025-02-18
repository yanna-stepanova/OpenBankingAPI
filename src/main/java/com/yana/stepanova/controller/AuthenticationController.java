package com.yana.stepanova.controller;

import com.yana.stepanova.dto.user.UserLoginRequestDto;
import com.yana.stepanova.dto.user.UserLoginResponseDto;
import com.yana.stepanova.dto.user.UserRegistrationRequestDto;
import com.yana.stepanova.dto.user.UserResponseDto;
import com.yana.stepanova.exception.RegistrationException;
import com.yana.stepanova.security.AuthenticationService;
import com.yana.stepanova.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@Validated
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        return userService.register(requestDto);
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }
}
