package com.yana.stepanova.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDto implements Serializable {
    private Long id;
    private String iban;
    private BigDecimal balance;
    private String currencyName;
    private Long userId;
    private String userEmail;
}
