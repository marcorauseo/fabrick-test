package com.fabrick.test.model.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Amount {


        private double debtorAmount;
        private String debtorCurrency;
        private double creditorAmount;
        private String creditorCurrency;
        private String creditorCurrencyDate;
        private double exchangeRate;

}
