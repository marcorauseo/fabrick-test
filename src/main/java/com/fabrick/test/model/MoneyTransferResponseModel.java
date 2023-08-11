package com.fabrick.test.model;

import com.fabrick.test.model.base.Account;
import com.fabrick.test.model.base.Address;
import com.fabrick.test.model.base.Amount;
import com.fabrick.test.model.base.Creditor;
import com.fabrick.test.model.base.Debtor;
import com.fabrick.test.model.base.Fee;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MoneyTransferResponseModel {

    private String status;
    private ArrayList<Object> errors;
    private Payload payload;

    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class Payload{


        private String moneyTransferId;
        private String status;
        private String direction;
        private Creditor creditor;
        private Debtor debtor;
        private String cro;
        private String uri;
        private String trn;
        private String description;
        private Date createdDatetime;
        private Date accountedDatetime;
        private Date debtorValueDate;
        private Date creditorValueDate;
        private Amount amount;
        @JsonProperty(value = "isUrgent")
        private Boolean isUrgent;
        @JsonProperty(value = "isInstant")
        private Boolean isInstant;
        private String feeType;
        private String feeAccountId;
        private List<Fee> fees;
        private Boolean hasTaxRelief;


    }
    }

