package com.yana.stepanova.dto.user;

import com.yana.stepanova.validation.FieldsValueMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@FieldsValueMatch(field = "password",
        fieldMatch = "repeatPassword",
        message = "These passwords must match")
public record UserRegistrationRequestDto(
        @NotBlank @Email String email,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank @Length(min = 8, max = 25) String password,
        @NotBlank @Length(min = 8, max = 25) String repeatPassword) {}
