package com.yana.stepanova.dto.payment;

import com.yana.stepanova.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentDto {
    private Long id;
    private String ibanFrom;
    private String ibanTo;
    private Long transactionId;
    private BigDecimal amount;
    private String currencyName;
    private Status status;
    private LocalDateTime createdAt;
}
