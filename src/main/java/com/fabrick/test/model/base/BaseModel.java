package com.fabrick.test.model.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BaseModel {


    private String status;
    private ArrayList<Object> error;
    private Object payload;



}
