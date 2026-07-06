package com.moneytransfer.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.moneytransfer.entity.Account;
import com.moneytransfer.repository.AccountRepository;
import com.moneytransfer.service.TransactionService;
import com.moneytransfer.utils.IDateUtils;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTests {

    @Mock
    private AccountRepository repository;

    @Mock
    private TransactionService transactionService;

    @Mock
    private IDateUtils dateUtils;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    public void deveRecusarTransferenciaPorSaldoInsuficiente() {
        Account fromAccount = new Account();
        fromAccount.setId(1L);
        fromAccount.setBalance(new BigDecimal("50.00")); 

        Account toAccount = new Account();
        toAccount.setId(2L);
        toAccount.setBalance(new BigDecimal("100.00"));

        BigDecimal transferAmount = new BigDecimal("100.00"); 

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            accountService.transfer(fromAccount, toAccount, transferAmount);
        });
        

        assertTrue(exception != null);
    }
}