package com.fabrick.test.service;

import com.fabrick.test.TestApplication;
import com.fabrick.test.entity.TransactionEntity;
import com.fabrick.test.model.AccountTransactionsResponseModel;
import com.fabrick.test.model.base.Transaction;
import com.fabrick.test.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class AccountTransactionsService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TransactionRepository transactionRepository;

    private static final Logger log = LoggerFactory.getLogger(TestApplication.class);


    private final String baseUrl = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/";

    public ResponseEntity<AccountTransactionsResponseModel> getAccountTransactions(String accountId, String fromAccountingDate, String toAccountingDate, String url, HttpEntity<?> entity) {
        log.info("getAccountTransactions: AccountId = {}, FromDate = {}, ToDate = {}", accountId, fromAccountingDate, toAccountingDate);

        try {
            AccountTransactionsResponseModel response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    AccountTransactionsResponseModel.class
            ).getBody();
            log.info("getAccountTransactions Response: {}", response);

            saveAccountTransactionsResponse(response);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (HttpStatusCodeException e) {
            log.error("getAccountTransactions Error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ResourceAccessException e) {
            log.error("getAccountTransactions Error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    private void saveAccountTransactionsResponse(AccountTransactionsResponseModel response) {
        List<Transaction> transactions = Optional.ofNullable(response.getPayload())
                .map(AccountTransactionsResponseModel.Payload::getList)
                .orElse(Collections.emptyList());


        transactions.forEach(transaction -> {
            TransactionEntity dbEntity = new TransactionEntity();

            Optional.ofNullable(transaction.getTransactionId()).ifPresent(assignTo(dbEntity::setTransactionId));
            Optional.ofNullable(transaction.getOperationId()).ifPresent(assignTo(dbEntity::setOperationId));
            Optional.ofNullable(transaction.getAccountingDate()).ifPresent(assignTo(dbEntity::setAccountingDate));
            Optional.ofNullable(transaction.getValueDate()).ifPresent(assignTo(dbEntity::setValueDate));

            if (dbEntity.getTransactionType() != null) {
                dbEntity.getTransactionType().setEnumeration((transaction.getTransactionType().getEnumeration()));
                dbEntity.getTransactionType().setValue((transaction.getTransactionType().getValue()));
            }

            if (dbEntity.getTransactionType() != null) {
                TransactionEntity.TransactionType dbTransactionType = new TransactionEntity.TransactionType();
                Optional.ofNullable(transaction.getTransactionType().getEnumeration()).ifPresent(assignTo(dbTransactionType::setEnumeration));
                Optional.ofNullable(transaction.getTransactionType().getValue()).ifPresent(assignTo(dbTransactionType::setValue));
                dbEntity.setTransactionType(dbTransactionType);
            }

            Optional.ofNullable(transaction.getAmount()).ifPresent(assignTo(dbEntity::setAmount));
            Optional.ofNullable(transaction.getCurrency()).ifPresent(assignTo(dbEntity::setCurrency));
            Optional.ofNullable(transaction.getDescription()).ifPresent(assignTo(dbEntity::setDescription));

            transactionRepository.save(dbEntity);
        });
    }
    private <T> Consumer<T> assignTo(Consumer<T> setter) {
        return setter;
    }

}

