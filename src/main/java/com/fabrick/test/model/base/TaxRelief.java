package com.fabrick.test.model.base;

import com.fabrick.test.model.MoneyTransferRequestModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TaxRelief {

    private String taxReliefId;
    @JsonProperty(value = "isCondoUpgrade")
    private Boolean isCondoUpgrade;
    private String creditorFiscalCode;
    private String beneficiaryType;
    private NaturalPersonBeneficiary naturalPersonBeneficiary;
    private LegalPersonBeneficiary legalPersonBeneficiary;

}
