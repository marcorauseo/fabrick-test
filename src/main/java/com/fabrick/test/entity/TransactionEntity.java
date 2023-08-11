package com.fabrick.test.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "TRANSACTION")
public class TransactionEntity {

    @Id
    @Column(name = "TRANSACTION_ID")
    private String transactionId;

    @Column(name = "OPERATION_ID")
    private String operationId;

    @Column(name = "ACCOUNTING_DATE")
    private String accountingDate;

    @Column(name = "VALUE_DATE")
    private String valueDate;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "enumeration", column = @Column(name = "TRANSACTION_TYPE_ENUM")),
            @AttributeOverride(name = "value", column = @Column(name = "TRANSACTION_TYPE_VALUE"))
    })
    private TransactionType transactionType;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "DESCRIPTION")
    private String description;



    @Embeddable
    @Data
    public static class TransactionType {

        @Column(name = "ENUMERATION")
        private String enumeration;

        @Column(name = "VALUE")
        private String value;

    }
}