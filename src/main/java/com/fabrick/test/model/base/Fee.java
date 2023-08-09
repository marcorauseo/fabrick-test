package com.fabrick.test.model.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Fee {

        private String feeCode;
        private String description;
        private double amount;
        private String currency;

}
