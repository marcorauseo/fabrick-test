package com.fabrick.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fabrick.test.controller.ApiController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fabrick.test.model.AccountBalanceResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@SpringJUnitConfig
@WebMvcTest(ApiController.class)
@AutoConfigureMockMvc
public class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RestTemplate restTemplate;

    private ObjectMapper objectMapper;

    private String baseUrl = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetBalance_Success() throws Exception {
        String accountId = "14537780";
        AccountBalanceResponseModel mockResponse = new AccountBalanceResponseModel();

        when(restTemplate.exchange(baseUrl + accountId + "/balance", HttpMethod.GET, null, AccountBalanceResponseModel.class))
                .thenReturn(new ResponseEntity<>(mockResponse, HttpStatus.OK));

        ResultActions resultActions = mockMvc.perform(get("/getBalance/{accountId}", accountId))
                .andExpect(status().isOk());


    }

    @Test
    public void testGetBalance_NotFound() throws Exception {
        String accountId = "123456789";


        when(restTemplate.exchange(baseUrl + accountId + "/balance", HttpMethod.GET, null, AccountBalanceResponseModel.class))
                .thenThrow(new HttpStatusCodeException(HttpStatus.NOT_FOUND, "Not Found") {});

        ResultActions resultActions = mockMvc.perform(get("/getBalance/{accountId}", accountId))
                .andExpect(status().isNotFound());


    }
}
