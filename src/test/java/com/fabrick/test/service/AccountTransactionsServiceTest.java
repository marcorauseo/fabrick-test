package com.fabrick.test.service;

import com.fabrick.test.entity.TransactionEntity;
import com.fabrick.test.model.AccountTransactionsResponseModel;
import com.fabrick.test.model.base.Transaction;
import com.fabrick.test.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AccountTransactionsServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private AccountTransactionsService accountTransactionsService;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        reset(restTemplate, transactionRepository);
    }


    @Test
    void testSaveAccountTransactionsResponse_EmptyPayload() {

        AccountTransactionsResponseModel response = new AccountTransactionsResponseModel();
        response.setPayload(null);


        accountTransactionsService.saveAccountTransactionsResponse(response);


        verify(transactionRepository, never()).save(any(TransactionEntity.class));
    }

    @Test
    void testSaveAccountTransactionsResponse_EmptyList() {

        AccountTransactionsResponseModel.Payload payload = new AccountTransactionsResponseModel.Payload();
        payload.setList(Collections.emptyList());
        AccountTransactionsResponseModel response = new AccountTransactionsResponseModel();
        response.setPayload(payload);

        accountTransactionsService.saveAccountTransactionsResponse(response);


        verify(transactionRepository, never()).save(any(TransactionEntity.class));
    }

    @Test
    void testSaveAccountTransactionsResponse_ValidResponse() {

        Transaction transaction = new Transaction();
        AccountTransactionsResponseModel.Payload payload = new AccountTransactionsResponseModel.Payload();
        payload.setList(Collections.singletonList(transaction));
        AccountTransactionsResponseModel response = new AccountTransactionsResponseModel();
        response.setPayload(payload);


        accountTransactionsService.saveAccountTransactionsResponse(response);


        verify(transactionRepository, times(1)).save(any(TransactionEntity.class));
    }
}
