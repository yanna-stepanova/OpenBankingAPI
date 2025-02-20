package com.yana.stepanova.service;

import com.yana.stepanova.dto.account.AccountDto;
import com.yana.stepanova.dto.account.CreateAccountRequestDTO;
import com.yana.stepanova.dto.transaction.TransactionDto;
import com.yana.stepanova.exception.EntityNotFoundCustomException;
import com.yana.stepanova.mapper.AccountMapper;
import com.yana.stepanova.mapper.TransactionMapper;
import com.yana.stepanova.model.Account;
import com.yana.stepanova.model.Transaction;
import com.yana.stepanova.model.User;
import com.yana.stepanova.repository.AccountRepository;
import com.yana.stepanova.repository.TransactionRepository;
import com.yana.stepanova.service.impl.AccountServiceImpl;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountRepository accountRepo;
    @Mock
    private AccountMapper accountMapper;
    @Mock
    private Random random;
    @Mock
    private TransactionRepository transactionRepo;
    @Mock
    private TransactionMapper transactionMapper;
    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    @DisplayName("""
    Get correct value of balance for existing accountId = iban
    """)
    void getAccountBalance_WithExistingAccountId_ReturnValidValue() {
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
    @DisplayName("""
    Get exception for non-existing accountId = iban
    """)
    void getAccountBalance_WithNonExistingAccountId_ReturnException() {
        //given
        String accountId = "UA111114560000020002011234511";

        Mockito.when(accountRepo.findByIban(accountId)).thenReturn(Optional.empty());

        //when
        Exception exception = Assertions.assertThrows(EntityNotFoundCustomException.class,
                () -> accountService.getAccountBalance(accountId));

        //then
        String expected = String.format("Account with IBAN %s not found", accountId);
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get correct AccountDto for valid requestDto")
    void save_WithValidRequestDto_ReturnAccountDto() {
        //given
        CreateAccountRequestDTO requestDTO = new CreateAccountRequestDTO(3L, "UAH",
                "38", "12", "123321", BigDecimal.valueOf(10000.00));
        Account account = new Account();
        User user = new User();
        user.setId(requestDTO.userId());
        user.setEmail("user@gmail.com");
        account.setUser(user);
        account.setBalance(requestDTO.balance());
        account.setCurrencyName(requestDTO.currencyName());

        AccountDto expected = new AccountDto();
        expected.setId(11L);
        expected.setIban(account.getIban());
        expected.setCurrencyName(account.getCurrencyName());
        expected.setBalance(account.getBalance());
        expected.setUserId(account.getUser().getId());
        expected.setUserEmail(account.getUser().getEmail());

        Mockito.when(accountMapper.toModel(requestDTO)).thenReturn(account);
        Mockito.when(accountRepo.save(account)).thenReturn(account);
        Mockito.when(accountMapper.toDto(account)).thenReturn(expected);

        //when
        AccountDto actual = accountService.save(requestDTO);

        //then
        Assertions.assertTrue(EqualsBuilder.reflectionEquals(expected, actual, "iban"));
    }

    @Test
    @DisplayName("Get the last 10 transactions by accountId = iban")
    void getAccountTransactions_WithExistingAccountId_ReturnsTwoTransactions() {
        // given
        String accountId = "UA131234560000020002011234566";

        Account accountFrom = new Account();
        accountFrom.setId(1L);
        accountFrom.setIban(accountId);

        Account accountTo = new Account();
        accountTo.setId(2L);
        accountTo.setIban("UA998877665544332211000012345");

        Transaction transaction1 = createTransaction(1L, accountFrom, accountTo,
                BigDecimal.valueOf(100), "UAH");
        Transaction transaction2 = createTransaction(2L, accountFrom, accountTo,
                BigDecimal.valueOf(200), "UAH");
        List<Transaction> transactions = List.of(transaction1, transaction2);

        TransactionDto transactionDto1 = new TransactionDto(
                transaction1.getId(),
                transaction1.getTransactionDate(),
                transaction1.getAmount(),
                transaction1.getCurrencyName(),
                transaction1.getAccountFrom().getId(),
                transaction1.getAccountFrom().getIban(),
                transaction1.getAccountTo().getId(),
                transaction1.getAccountTo().getIban());
        TransactionDto transactionDto2 = new TransactionDto(
                transaction2.getId(),
                transaction2.getTransactionDate(),
                transaction2.getAmount(),
                transaction2.getCurrencyName(),
                transaction2.getAccountFrom().getId(),
                transaction2.getAccountFrom().getIban(),
                transaction2.getAccountTo().getId(),
                transaction2.getAccountTo().getIban());
        List<TransactionDto> expected = List.of(transactionDto1, transactionDto2);

        Mockito.when(transactionRepo.findAllByAccountId(accountId)).thenReturn(transactions);
        Mockito.when(transactionMapper.toDto(transaction1)).thenReturn(transactionDto1);
        Mockito.when(transactionMapper.toDto(transaction2)).thenReturn(transactionDto2);

        // when
        List<TransactionDto> actual = accountService.getAccountTransactions(accountId);

        // then
        Assertions.assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            Assertions.assertTrue(
                    EqualsBuilder.reflectionEquals(expected.get(i), actual.get(i)));
        }
    }

    private Transaction createTransaction(Long id, Account from, Account to, BigDecimal amount, String currency) {
        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setAccountFrom(from);
        transaction.setAccountTo(to);
        transaction.setAmount(amount);
        transaction.setCurrencyName(currency);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setDescription("Test transaction");
        transaction.setDeleted(false);
        return transaction;
    }


}
