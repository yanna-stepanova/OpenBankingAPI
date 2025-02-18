package com.yana.stepanova.service;

import com.yana.stepanova.dto.payment.CreatePaymentRequestDto;
import com.yana.stepanova.dto.payment.PaymentDto;


public interface PaymentService {

    PaymentDto initiatePayment(CreatePaymentRequestDto requestDto);
}
