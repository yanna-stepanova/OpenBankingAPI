package com.yana.stepanova.dto.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateAccountRequestDTO(@NotNull @Positive Long userId,
                                      @NotBlank @Size(min = 3) String currencyName,
                                      @NotBlank @Size(min = 2) String countryCode,
                                      @NotBlank @Size(min = 2) String checkCode,
                                      @NotBlank @Size(min = 6) String bankCode,
                                      @NotNull @Positive BigDecimal balance) {}
