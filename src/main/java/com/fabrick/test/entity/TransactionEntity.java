package com.fabrick.test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class TransactionEntity {

    @Id
    @Column(name="TRANSACTION_ID")
    public String transactionId;
    @Column(name="OPERATION_ID")
    public String operationId;
    @Column(name="ACCOUNTING_DATE")
    public String accountingDate;
    @Column(name="VALUE_DATE")
    public String valueDate;
    @Column(name="TRANSACTION_TYPE")
    public Object transactionType;
    @Column(name="AMOUNT")
    public int amount;
    @Column(name="CURRENCY")
    public String currency;
    @Column(name="DESCRIPTION")
    public String description;



}
