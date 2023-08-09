package com.fabrick.test.model.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LegalPersonBeneficiary {
    private String fiscalCode;
    private String legalRepresentativeFiscalCode;
}
