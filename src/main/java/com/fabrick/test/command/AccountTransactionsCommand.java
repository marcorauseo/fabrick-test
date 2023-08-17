package com.fabrick.test.command;


import com.fabrick.test.model.AccountTransactionsResponseModel;
import com.fabrick.test.service.AccountTransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class AccountTransactionsCommand {
        @Autowired
        private AccountTransactionsService transactionsService;

        public ResponseEntity<AccountTransactionsResponseModel> execute(String accountId, String fromAccountingDate, String toAccountingDate, String url, HttpEntity<?> entity) {
            return transactionsService.getAccountTransactions(accountId, fromAccountingDate, toAccountingDate,url,entity);
        }
    }



