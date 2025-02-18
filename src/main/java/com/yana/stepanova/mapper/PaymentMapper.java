package com.yana.stepanova.mapper;

import com.yana.stepanova.config.MapperConfig;
import com.yana.stepanova.dto.payment.CreatePaymentRequestDto;
import com.yana.stepanova.dto.payment.PaymentDto;
import com.yana.stepanova.model.Payment;
import com.yana.stepanova.model.Status;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, uses = TransactionMapper.class)
public interface PaymentMapper {
    @Mapping(target = "status", ignore = true)
    @Mapping(source = "transactionId", target = "transaction.id")
    Payment toModel(CreatePaymentRequestDto requestDto);

    @Mapping(source = "transaction.id", target = "transactionId")
    @Mapping(source = "transaction.accountFrom.iban", target = "ibanFrom")
    @Mapping(source = "transaction.accountTo.iban", target = "ibanTo")
    PaymentDto toDto(Payment payment);

    @AfterMapping
    default void setStatus(@MappingTarget Payment payment,
                         CreatePaymentRequestDto requestDto) {
        if (requestDto.statusName() != null) {
            payment.setStatus(Status.valueOf(requestDto.statusName().toUpperCase()));
        }
    }
}
