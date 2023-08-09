package com.fabrick.test.model;

import com.fabrick.test.model.base.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AccountTransactionsResponseModel {

    private String status;
    private ArrayList<Object> error;
    private Payload payload;


    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class Payload {
        private List<Transaction> list;
    }

}
