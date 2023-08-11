package com.fabrick.test.model.base;

import com.fabrick.test.model.AccountTransactionsResponseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Transaction {

    private String transactionId;
    private String operationId;
    private String accountingDate;
    private String valueDate;
    private TransactionType transactionType;
    private BigDecimal amount;
    private String currency;
    private String description;

    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class TransactionType {
        private String enumeration;
        private String value;
    }

}
