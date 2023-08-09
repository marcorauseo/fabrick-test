package com.fabrick.test;

import com.fabrick.test.controller.ApiController;
import com.fabrick.test.model.AccountBalance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApiControllerTest {

    private ApiController apiController;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        apiController = new ApiController(restTemplate, "sandboxAuthSchema", "sandboxApiKey");

    }

    @Test
    void testGetBalance_Success() {
        String accountId = "14537780";
        String url = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/" + accountId + "/balance";
        LocalDate date = LocalDate.of(2018, 8, 17);
        AccountBalance expectedResponse = new AccountBalance(date,29.64,29.64,"EUR");
        Mockito.when(restTemplate.getForObject(url, AccountBalance.class)).thenReturn(expectedResponse);

        ResponseEntity<AccountBalance> responseEntity = apiController.getBalance(accountId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void testGetBalance_NotFound() {
        String accountId = "123456";
        String url = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/" + accountId + "/balance";

        Mockito.when(restTemplate.getForObject(url, AccountBalance.class)).thenThrow(new HttpStatusCodeException(HttpStatus.NOT_FOUND) {});

        ResponseEntity<AccountBalance> responseEntity = apiController.getBalance(accountId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testGetBalance_ServiceUnavailable() {
        String accountId = "123456";
        String url = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/" + accountId + "/balance";

        Mockito.when(restTemplate.getForObject(url, AccountBalance.class)).thenThrow(new ResourceAccessException("Service Unavailable"));

        ResponseEntity<AccountBalance> responseEntity = apiController.getBalance(accountId);

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, responseEntity.getStatusCode());
    }
}

