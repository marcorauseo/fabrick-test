package com.fabrick.test.model;

import com.fabrick.test.model.base.Account;
import com.fabrick.test.model.base.Address;
import com.fabrick.test.model.base.Creditor;
import com.fabrick.test.model.base.LegalPersonBeneficiary;
import com.fabrick.test.model.base.NaturalPersonBeneficiary;
import com.fabrick.test.model.base.TaxRelief;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MoneyTransferRequestModel {



    private Creditor creditor;
    private String executionDate;
    private String uri;
    private String description;
    private double amount;
    private String currency;
    @JsonProperty(value = "isUrgent")
    private Boolean isUrgent;
    @JsonProperty(value = "isInstant")
    private Boolean isInstant;
    private String feeType;
    private String feeAccountId;
    private TaxRelief taxRelief;

    }
