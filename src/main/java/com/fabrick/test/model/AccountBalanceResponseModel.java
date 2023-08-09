package com.fabrick.test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AccountBalanceResponseModel {

    private String status;
    private ArrayList<Object> error;
    private Payload payload;



    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class Payload{
        private String date;
        private double balance;
        private double availableBalance;
        private String currency;
    }

}
