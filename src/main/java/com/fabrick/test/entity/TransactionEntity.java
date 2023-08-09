package com.fabrick.test.entity;



import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;

@Entity
public class TransactionEntity {

    @Id
    private String transactionId;

    private String operationId;
    private String accountingDate;
    public String valueDate;
    public Object transactionType;
    public int amount;
    public String currency;
    public String description;



    // Getter e setter
}

