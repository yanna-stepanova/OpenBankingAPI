package com.yana.stepanova.controller;

import com.yana.stepanova.dto.account.AccountDto;
import com.yana.stepanova.dto.account.CreateAccountRequestDTO;
import com.yana.stepanova.dto.transaction.TransactionDto;
import com.yana.stepanova.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Account manager", description = "Endpoints for managing accounts")
@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
@Validated
public class AccountController {
    private final AccountService accountService;

    @Operation(summary = "Create a new user's account",
               description = "Create a new user's account based on the currency")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    @PostMapping
    public AccountDto createAccount(@RequestBody @Valid CreateAccountRequestDTO requestDTO) {
        return accountService.save(requestDTO);
    }

    @Operation(summary = "Get the account balance by accountId(IBAN)",
            description = "Returns the current account balance: accountId is IBAN")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    @GetMapping("/{accountId}/balance")
    public AccountDto getAccountBalance(@PathVariable String accountId) {
        return accountService.getAccountBalance(accountId);
    }

    @Operation(summary = "Get last 10 transactions by accountId(IBAN)",
            description = "Get the last 10 transactions for the given account")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    @GetMapping("/{accountId}/transactions")
    public List<TransactionDto> getAccountTransactions(@PathVariable String accountId) {
        return accountService.getAccountTransactions(accountId);
    }
}
