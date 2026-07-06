package com.moneytransfer.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.moneytransfer.entity.Account;
import com.moneytransfer.repository.AccountRepository;
import com.moneytransfer.service.AccountService;

@SpringBootTest
@Transactional 
public class AccountServiceIntegrationTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void deveTransferirSaldoAtualizandoBancoDeDados() {
        Account fromAccount = new Account();
        fromAccount.setBalance(new BigDecimal("500.00"));
        fromAccount = accountRepository.save(fromAccount);

        Account toAccount = new Account();
        toAccount.setBalance(new BigDecimal("100.00"));
        toAccount = accountRepository.save(toAccount);

        BigDecimal transferAmount = new BigDecimal("150.00");

        accountService.transfer(fromAccount, toAccount, transferAmount);

        
        Account fromAccountUpdated = accountRepository.findById(fromAccount.getId()).get();
        Account toAccountUpdated = accountRepository.findById(toAccount.getId()).get();

      
        assertEquals(0, new BigDecimal("350.00").compareTo(fromAccountUpdated.getBalance()));
        
        assertEquals(0, new BigDecimal("250.00").compareTo(toAccountUpdated.getBalance()));
    }
}