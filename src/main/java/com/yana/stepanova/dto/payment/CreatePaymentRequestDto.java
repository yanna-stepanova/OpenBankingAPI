package com.yana.stepanova.dto.payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreatePaymentRequestDto(@NotNull @Positive Long transactionId,
                                      @NotBlank String statusName) {
}
