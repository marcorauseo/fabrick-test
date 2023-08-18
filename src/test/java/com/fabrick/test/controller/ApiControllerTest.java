package com.fabrick.test.controller;

import com.fabrick.test.command.AccountTransactionsCommand;
import com.fabrick.test.controller.ApiController;
import com.fabrick.test.model.AccountBalanceResponseModel;
import com.fabrick.test.model.AccountTransactionsResponseModel;
import com.fabrick.test.model.MoneyTransferRequestModel;
import com.fabrick.test.model.MoneyTransferResponseModel;
import com.fabrick.test.model.base.Transaction;
import com.fabrick.test.repository.TransactionRepository;
import com.fabrick.test.service.AccountTransactionsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ApiControllerTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TransactionRepository transactionRepository;



    @InjectMocks
    private ApiController apiController;
    @InjectMocks
    private AccountTransactionsCommand transactionsCommand;
    @InjectMocks
    private AccountTransactionsService transactionsService;

    @Test
    public void testGetBalance_Success() {
        // Mocking
        String accountId = "14537780";
        AccountBalanceResponseModel responseModel = new AccountBalanceResponseModel();
        ResponseEntity<AccountBalanceResponseModel> responseEntity = new ResponseEntity<>(responseModel, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(AccountBalanceResponseModel.class)))
                .thenReturn(responseEntity);

        // Test
        ResponseEntity<AccountBalanceResponseModel> result = apiController.getBalance(accountId);

        // Assertions
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseModel, result.getBody());
    }

    @Test
    public void testGetBalance_HttpStatusCodeException() {
        // Mocking
        String accountId = "123";
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(AccountBalanceResponseModel.class)))
                .thenThrow(new HttpStatusCodeException(HttpStatus.NOT_FOUND, "Not Found") {});

        // Test
        ResponseEntity<AccountBalanceResponseModel> result = apiController.getBalance(accountId);

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    public void testGetBalance_ResourceAccessException() {
        // Mocking
        String accountId = "123";
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(AccountBalanceResponseModel.class)))
                .thenThrow(new ResourceAccessException("Service Unavailable"));

        // Test
        ResponseEntity<AccountBalanceResponseModel> result = apiController.getBalance(accountId);


        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    public void testPostMoneyTransfers_Success() throws JsonProcessingException {
        // Mocking
        String accountId = "123";
        MoneyTransferRequestModel request = new MoneyTransferRequestModel(); // Initialize as needed
        MoneyTransferResponseModel responseModel = new MoneyTransferResponseModel(); // Initialize as needed
        ResponseEntity<MoneyTransferResponseModel> responseEntity = new ResponseEntity<>(responseModel, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(MoneyTransferResponseModel.class)))
                .thenReturn(responseEntity);


        ResponseEntity<MoneyTransferResponseModel> result = apiController.postMoneyTransfers(accountId, request);


        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseModel, result.getBody());
    }



}
