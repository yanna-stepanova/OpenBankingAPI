package com.yana.stepanova.controller;

import com.yana.stepanova.dto.payment.CreatePaymentRequestDto;
import com.yana.stepanova.dto.payment.PaymentDto;
import com.yana.stepanova.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Payment manager", description = "Endpoints for managing payments")
@RequiredArgsConstructor
@RestController
@RequestMapping("/payments")
@Validated
public class PaymentController {
    private final PaymentService paymentService;

    @Operation(summary = "Initiates a new payment",
            description = "Initiates a new payment to an external bank API")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    @PostMapping("/initiate")
    public PaymentDto initiatePayment(@RequestBody @Valid CreatePaymentRequestDto requestDto) {
        return paymentService.initiatePayment(requestDto);
    }
}
