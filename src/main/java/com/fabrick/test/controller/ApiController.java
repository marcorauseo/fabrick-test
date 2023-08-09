package com.fabrick.test.controller;


import com.fabrick.test.TestApplication;
import com.fabrick.test.model.AccountBalance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;



@RestController
public class ApiController {



    public final RestTemplate restTemplate;
    public final String sandboxAuthSchema;
    public final String sandboxApiKey;

    private static final Logger log = LoggerFactory.getLogger(TestApplication.class);

    @Autowired
    public ApiController(RestTemplate restTemplate, @Value("${sandbox.auth.schema}") String sandboxAuthSchema,@Value("${sandbox.api.key}") String sandboxApiKey) {
        this.restTemplate = restTemplate;
        this.sandboxAuthSchema = sandboxAuthSchema;
        this.sandboxApiKey = sandboxApiKey;
    }

    @GetMapping("/getBalance/{accountId}")
    public ResponseEntity<AccountBalance> getBalance(@PathVariable String accountId) {
        log.info("get-cash-account");

        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", "application/json");
        headers.set("Auth-Schema", sandboxAuthSchema);
        headers.set("Api-Key", sandboxApiKey);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        String url = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/" + accountId + "/balance";
        try {
            AccountBalance response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    AccountBalance.class
            ).getBody();
        log.info(String.valueOf(response));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (HttpStatusCodeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ResourceAccessException e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }






    @GetMapping("/error")
    public String error() {
        return "ops";
    }
}