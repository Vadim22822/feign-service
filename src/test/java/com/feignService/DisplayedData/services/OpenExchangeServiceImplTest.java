package com.feignService.DisplayedData.services;

import com.feignService.FeignClients.ExchangeRateClient;
import com.feignService.Responses.ExchangeRateResponses.ExchangeRateResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class OpenExchangeServiceImplTest {

    @MockBean
    private ExchangeRateClient exchangeRateClient;
    private OpenExchangeService exchangeRatesService;
    private ExchangeRateResponse testResponse;
    private final String curCode = "RUB";

    @Autowired
    public void setExchangeRatesService(OpenExchangeService exchangeRatesService) {
        this.exchangeRatesService = exchangeRatesService;
    }

    @BeforeEach
    public void init() {

        testResponse = new ExchangeRateResponse();
        testResponse.setError(false);
        Map<String, BigDecimal> rates = new HashMap<>();
        rates.put("RUB", new BigDecimal("50.05"));
        testResponse.setRates(rates);

    }

    @Test
    void getLatestRate() throws Exception {

        Mockito.when(exchangeRateClient.getLatestRate(anyString(), anyString(), anyString())).thenReturn(testResponse);
        ExchangeRateResponse response = exchangeRatesService.getLatestRate(curCode);

        assertNotNull(response);
        assertEquals(response.getRate(), testResponse.getRate());
        assertEquals(response.getComparedCurrency(), testResponse.getComparedCurrency());
        assertEquals(response.getRates(), testResponse.getRates());

    }

    @Test
    void getHistoricalRate() throws Exception {

        String anyString = "2022-06-12";
        Mockito.when(exchangeRateClient.getHistoricalRate(any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(testResponse);
        ExchangeRateResponse response = exchangeRatesService.getHistoricalRate(curCode, anyString);

        assertNotNull(response);
        assertEquals(response.getRate(), testResponse.getRate());
        assertEquals(response.getComparedCurrency(), testResponse.getComparedCurrency());
        assertEquals(response.getRates(), testResponse.getRates());

    }
}