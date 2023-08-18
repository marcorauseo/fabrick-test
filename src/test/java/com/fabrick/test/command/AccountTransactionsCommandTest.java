package com.fabrick.test.command;

import com.fabrick.test.model.AccountTransactionsResponseModel;
import com.fabrick.test.service.AccountTransactionsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountTransactionsCommandTest {

    @Mock
    private AccountTransactionsService transactionsService;

    @InjectMocks
    private AccountTransactionsCommand transactionsCommand;

    @Before
    public void setUp() {
        // Inizializzazione dei mock e delle dipendenze, se necessario
    }

    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", "application/json");
        headers.set("Auth-Schema", "sandboxAuthSchema");
        headers.set("Api-Key", "sandboxApiKey");
        return headers;
    }

    @Test
    public void testExecute_Success() {
        // Preparazione dati di test
        String accountId = "123";
        String fromAccountingDate = "2023-01-01";
        String toAccountingDate = "2023-12-31";
        String url = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/123/transactions?fromAccountingDate=2023-01-01&toAccountingDate=2023-12-31";
        String body = "request body";
        HttpHeaders headers = getHeaders();
        HttpEntity<?> entity = new HttpEntity<>(body,headers);

        // Mock del servizio
        AccountTransactionsResponseModel responseModel = new AccountTransactionsResponseModel();
        ResponseEntity<AccountTransactionsResponseModel> responseEntity = new ResponseEntity<>(responseModel, HttpStatus.OK);
        when(transactionsService.getAccountTransactions(eq(accountId), eq(fromAccountingDate), eq(toAccountingDate), eq(url), any(HttpEntity.class)))
                .thenReturn(responseEntity);

        // Esecuzione del metodo da testare
        ResponseEntity<AccountTransactionsResponseModel> result = transactionsCommand.execute(accountId, fromAccountingDate, toAccountingDate, url, entity);

        // Verifica dei risultati
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseModel, result.getBody());

        // Verifica delle chiamate al mock del servizio
        verify(transactionsService, times(1)).getAccountTransactions(eq(accountId), eq(fromAccountingDate), eq(toAccountingDate), eq(url), any(HttpEntity.class));
    }
}
