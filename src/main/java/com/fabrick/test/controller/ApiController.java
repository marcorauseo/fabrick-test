package com.fabrick.test.controller;


import com.fabrick.test.TestApplication;
import com.fabrick.test.command.AccountTransactionsCommand;
import com.fabrick.test.repository.TransactionRepository;
import com.fabrick.test.entity.TransactionEntity;
import com.fabrick.test.model.AccountBalanceResponseModel;
import com.fabrick.test.model.AccountTransactionsResponseModel;
import com.fabrick.test.model.MoneyTransferRequestModel;
import com.fabrick.test.model.MoneyTransferResponseModel;
import com.fabrick.test.model.base.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;


@RestController
public class ApiController {




    public final RestTemplate restTemplate;
    public final String sandboxAuthSchema;
    public final String sandboxApiKey;
    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", "application/json");
        headers.set("Auth-Schema", sandboxAuthSchema);
        headers.set("Api-Key", sandboxApiKey);
        return headers;
    }

    private final String baseUrl = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/";

    private static final Logger log = LoggerFactory.getLogger(TestApplication.class);



    private final TransactionRepository transactionRepository;



    @Autowired
    private AccountTransactionsCommand transactionsCommand;



    @Autowired
    public ApiController(ObjectMapper objectMapper,RestTemplate restTemplate, @Value("${sandbox.auth.schema}") String sandboxAuthSchema, @Value("${sandbox.api.key}") String sandboxApiKey
    , TransactionRepository transactionRepository) {
        this.transactionRepository =transactionRepository;
        this.restTemplate = restTemplate;
        this.sandboxAuthSchema = sandboxAuthSchema;
        this.sandboxApiKey = sandboxApiKey;

    }

    @GetMapping("/getBalance/{accountId}")
    public ResponseEntity<AccountBalanceResponseModel> getBalance(@PathVariable String accountId) {
        log.info("getBalance: AccountId = {}", accountId);
        HttpHeaders headers = getHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        String url = baseUrl + accountId + "/balance";
        try {
            AccountBalanceResponseModel response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    AccountBalanceResponseModel.class
            ).getBody();
            log.info("getBalance Response: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (HttpStatusCodeException e) {
            log.error("getBalance Error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ResourceAccessException e) {
            log.error("getBalance Error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PostMapping("/payments/money-transfers/{accountId}")
    public ResponseEntity<MoneyTransferResponseModel> postMoneyTransfers(@PathVariable String accountId, @RequestBody MoneyTransferRequestModel request) {
        log.info("postMoneyTransfers: AccountId = {}, Request = {}", accountId, request);
        HttpHeaders headers = getHeaders();
        HttpEntity<MoneyTransferRequestModel> entity = new HttpEntity<>(request, headers);
        String url = baseUrl + accountId + "/payments/money-transfers";

        try {

            MoneyTransferResponseModel response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    MoneyTransferResponseModel.class
            ).getBody();
            log.info("postMoneyTransfers Response: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (HttpStatusCodeException e) {
            MoneyTransferResponseModel responseModel = e.getResponseBodyAs(MoneyTransferResponseModel.class);
            log.error("postMoneyTransfers Error: {}", e.getMessage());
            return new ResponseEntity<>(responseModel,e.getStatusCode());
        } catch (ResourceAccessException e) {
            log.error("postMoneyTransfers Error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }


    @GetMapping("/transactions/{accountId}")
    public ResponseEntity<AccountTransactionsResponseModel> getAccountTransactions(@PathVariable String accountId, @RequestParam(name = "fromAccountingDate") String fromAccountingDate,
                                                                                   @RequestParam(name = "toAccountingDate") String toAccountingDate, @RequestBody String body)  {
        log.info("getAccountTransactions: AccountId = {}, FromDate = {}, ToDate = {}", accountId, fromAccountingDate, toAccountingDate);
        HttpHeaders headers = getHeaders();
        HttpEntity<?> entity = new HttpEntity<>(body,headers);
        String url = baseUrl + accountId + "/transactions" + "?fromAccountingDate=" + fromAccountingDate + "&toAccountingDate=" + toAccountingDate;

        return transactionsCommand.execute(accountId, fromAccountingDate, toAccountingDate,url, entity);
    }



    @GetMapping("/error")
    public String error() {
        return "ops qualcosa è andato storto";
    }
}