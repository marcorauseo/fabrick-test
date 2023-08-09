package com.fabrick.test.model.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Address {


        private String address;
        private String city;
        private String countryCode;

        // getter and setter methods

}
