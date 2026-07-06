package com.moneytransfer.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moneytransfer.dto.CreateTransferDto;
import com.moneytransfer.entity.Account;
import com.moneytransfer.repository.AccountRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerSystemTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void deveProcessarRequisicaoHttpDeTransferenciaComSucesso() throws Exception {
        Account fromAccount = new Account();
        fromAccount.setBalance(new BigDecimal("1000.00"));
        fromAccount = accountRepository.save(fromAccount);

        Account toAccount = new Account();
        toAccount.setBalance(new BigDecimal("0.00"));
        toAccount = accountRepository.save(toAccount);

        CreateTransferDto payload = new CreateTransferDto();
        payload.setToAccountId(toAccount.getId());
        payload.setAmount(new BigDecimal("200.00"));

        String jsonPayload = objectMapper.writeValueAsString(payload);

        mockMvc.perform(post("/account/" + fromAccount.getId() + "/transfers")
                .contentType(MediaType.APPLICATION_JSON)
            .header("If-Match", fromAccount.getVersion().toString())
                .content(jsonPayload))
                .andExpect(status().isOk()); 
    }
}