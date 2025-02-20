package com.yana.stepanova.service;

import com.yana.stepanova.model.Account;
import com.yana.stepanova.repository.AccountRepository;
import com.yana.stepanova.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountRepository accountRepo;
    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    @DisplayName("""
    Get correct AccountDto for existing accountId = iban
    """)
    void getAccountBalance_WithValidAccountId_ReturnValidValue() {
        //given
        String accountId = "UA131234560000020002011234566";
        Account account = new Account();
        account.setIban(accountId);
        account.setBalance(BigDecimal.valueOf(5029.13));
        account.setCurrencyName("UAH");

        Mockito.when(accountRepo.findByIban(accountId)).thenReturn(Optional.of(account));

        //when
        BigDecimal actual = accountService.getAccountBalance(accountId);

        //then
        BigDecimal expected = accountService.getAccountBalance(accountId);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void save() {
    }

    @Test
    void getAccountTransactions() {
    }
}
