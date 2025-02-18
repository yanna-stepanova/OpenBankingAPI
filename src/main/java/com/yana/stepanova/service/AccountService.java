package com.yana.stepanova.service;

import com.yana.stepanova.dto.account.AccountDto;
import com.yana.stepanova.dto.account.CreateAccountRequestDTO;
import com.yana.stepanova.dto.transaction.TransactionDto;
import com.yana.stepanova.exception.RegistrationException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {

    AccountDto getAccountBalance(String accountId);

    AccountDto save(CreateAccountRequestDTO requestDTO);

    List<TransactionDto> getAccountTransactions(String accountId);
}
