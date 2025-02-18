package com.yana.stepanova.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UserRoleRequestDto(@NotBlank String roleName) {
}
