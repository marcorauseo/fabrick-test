package com.fabrick.test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;


@Entity
@Data
@Table(name = "TRANSACTION")
public class TransactionEntity {

    @Id
    @Column(name="TRANSACTION_ID")
    public String transactionId;
    @Column(name="OPERATION_ID")
    public String operationId;
    @Column(name="ACCOUNTING_DATE")
    public String accountingDate;
    @Column(name="VALUE_DATE")
    public String valueDate; // non va a db
    @Column(name="AMOUNT")
    public BigDecimal amount; // string perchè puo' essere negativo
    @Column(name="CURRENCY")
    public String currency;  // non va a db
    @Column(name="DESCRIPTION")
    public String description; // non va a db



}
