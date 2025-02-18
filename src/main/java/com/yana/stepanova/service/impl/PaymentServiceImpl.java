package com.yana.stepanova.service.impl;

import com.yana.stepanova.dto.payment.CreatePaymentRequestDto;
import com.yana.stepanova.dto.payment.PaymentDto;
import com.yana.stepanova.exception.EntityNotFoundCustomException;
import com.yana.stepanova.mapper.PaymentMapper;
import com.yana.stepanova.model.Payment;
import com.yana.stepanova.model.Transaction;
import com.yana.stepanova.repository.PaymentRepository;
import com.yana.stepanova.repository.TransactionRepository;
import com.yana.stepanova.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    private final TransactionRepository transactionRepo;
    private final PaymentRepository paymentRepo;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentDto initiatePayment(CreatePaymentRequestDto requestDto) {
        Payment payment = paymentMapper.toModel(requestDto);
        Long transactionId = payment.getTransaction().getId();
        Transaction transaction = transactionRepo.findById(transactionId)
                .orElseThrow(() -> new EntityNotFoundCustomException(
                        String.format("Transaction with id: %s not found", transactionId)));
        payment.setTransaction(transaction);
        payment.setAmount(transaction.getAmount());
        payment.setCurrencyName(transaction.getCurrencyName());
        payment.setCreatedAt(LocalDateTime.now());
        return paymentMapper.toDto(paymentRepo.save(payment));
    }
}
