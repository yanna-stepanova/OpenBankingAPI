package com.yana.stepanova.service.impl;

import com.yana.stepanova.dto.account.AccountDto;
import com.yana.stepanova.dto.account.CreateAccountRequestDTO;
import com.yana.stepanova.dto.transaction.TransactionDto;
import com.yana.stepanova.exception.EntityNotFoundCustomException;
import com.yana.stepanova.mapper.AccountMapper;
import com.yana.stepanova.mapper.TransactionMapper;
import com.yana.stepanova.model.Account;
import com.yana.stepanova.repository.AccountRepository;
import com.yana.stepanova.repository.TransactionRepository;
import com.yana.stepanova.service.AccountService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private final Random myRandom;
    private final AccountRepository accountRepo;
    private final AccountMapper accountMapper;
    private final TransactionRepository transactionRepo;
    private final TransactionMapper transactionMapper;

    @Override
    public BigDecimal getAccountBalance(String accountId) {
        return accountRepo.findByIban(accountId).orElseThrow(() -> new EntityNotFoundCustomException(
                String.format("Account with IBAN %s not found", accountId))).getBalance();
    }

    @Override
    public AccountDto save(CreateAccountRequestDTO requestDTO) {
        Account account = accountMapper.toModel(requestDTO);
        if (account.getIban() == null || account.getIban().isBlank()) {
            account.setIban(generateIbanNumber(
                    requestDTO.countryCode(),
                    requestDTO.checkCode(),
                    requestDTO.bankCode(),
                    account.getUser().getId()));
        }
        return accountMapper.toDto(accountRepo.save(account));
    }

    @Override
    public List<TransactionDto> getAccountTransactions(String accountId) {
        return transactionRepo.findAllByAccountId(accountId).stream()
                .map(transactionMapper::toDto)
                .toList();
    }

    private String generateIbanNumber(String countryCode, String bankCode, String checkCode, Long userId) {
        return String.format("%s%s%s%s%s", countryCode, checkCode, bankCode,
                String.format("%06d", userId), generateUniqueCode());
    }

    private String generateUniqueCode() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 13; i++) {
            result.append(myRandom.nextInt(10));
        }
        return result.toString();
    }
}
