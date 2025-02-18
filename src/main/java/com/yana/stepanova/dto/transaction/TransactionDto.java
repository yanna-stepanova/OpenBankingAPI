package com.yana.stepanova.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class TransactionDto implements Serializable {
    private final Long id;
    private final LocalDateTime transactionDate;
    private final BigDecimal amount;
    private final String currencyName;
    private final Long accountFromId;
    private final String ibanFrom;
    private final Long accountToId;
    private final String ibanTo;
}
