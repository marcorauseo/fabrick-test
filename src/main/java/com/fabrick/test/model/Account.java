package com.fabrick.test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Account {

        private String accountId;
        private String iban;
        private String abiCode;
        private String cabCode;
        private String countryCode;
        private String internationalCin;
        private String nationalCin;
        private String account;
        private String alias;
        private String productName;
        private String holderName;
        private LocalDate activatedDate;
        private String currency;

}


