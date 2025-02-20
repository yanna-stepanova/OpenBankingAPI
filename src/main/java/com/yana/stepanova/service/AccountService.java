package com.yana.stepanova.service;

import com.yana.stepanova.dto.account.AccountDto;
import com.yana.stepanova.dto.account.CreateAccountRequestDTO;
import com.yana.stepanova.dto.transaction.TransactionDto;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    BigDecimal getAccountBalance(String accountId);

    AccountDto save(CreateAccountRequestDTO requestDTO);

    List<TransactionDto> getAccountTransactions(String accountId);
}
