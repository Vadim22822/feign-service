package com.feignService.Responses.ExchangeRateResponses;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ExchangeRateResponseTest {

    private ExchangeRateResponse testResponse;
    private String bigDecimal;
    private String currency;

    @BeforeEach
    public void init() {

        bigDecimal = "50.05";
        currency = "RUB";
        testResponse = new ExchangeRateResponse();
        testResponse.setError(true);
        Map<String, BigDecimal> rates = new HashMap<>();
        rates.put(currency, new BigDecimal(bigDecimal));
        testResponse.setRates(rates);

    }

    @Test
    void isError() {

        assertTrue(testResponse.isError());

    }

    @Test
    void compareRates() throws Exception {

        ExchangeRateResponse test1 = new ExchangeRateResponse();
        Map<String, BigDecimal> rates = new HashMap<>();
        rates.put(currency, new BigDecimal("60"));
        test1.setRates(rates);

        int result = testResponse.compareRates(test1);
        assertTrue(result < 0);

        result = test1.compareRates(testResponse);
        assertTrue(result > 0);

        result = test1.compareRates(test1);
        assertEquals(0, result);

        rates.clear();
        rates.put(currency, new BigDecimal(bigDecimal));
        test1.setRates(rates);
        result = test1.compareRates(testResponse);
        assertEquals(0, result);

    }

    @Test
    void getRate() {

        assertEquals(testResponse.getRate(), new BigDecimal(bigDecimal));

    }

    @Test
    void getComparedCurrency() {

        assertEquals(testResponse.getComparedCurrency(), currency);

    }

}