package com.feignService.DisplayedData.services;

import com.feignService.DisplayedData.DisplayedData;
import com.feignService.Responses.ExchangeRateResponses.ExchangeRateResponse;
import com.feignService.Responses.GiphyResponses.GIFObject;
import com.feignService.Responses.GiphyResponses.GiphyResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
class GIFGettingServiceImplTest {

    @MockBean
    private OpenExchangeService openExchangeService;
    @MockBean
    private GiphyService giphyService;
    private GIFGettingService gifGettingService;
    private ExchangeRateResponse currentDayERResponse;
    private ExchangeRateResponse previousDayERResponse;
    private GiphyResponse giphyResponse;
    private DisplayedData testDisplayedData;
    private String curCode = "RUB";

    @Autowired
    public void setExchangeRateClient(OpenExchangeService openExchangeService) {
        this.openExchangeService = openExchangeService;
    }

    @Autowired
    public void setGiphyService(GiphyService giphyService) {
        this.giphyService = giphyService;
    }

    @Autowired
    public void setGifGettingService(GIFGettingService gifGettingService) {
        this.gifGettingService = gifGettingService;
    }

    @BeforeEach
    public void init() {

        currentDayERResponse = new ExchangeRateResponse();
        Map<String, BigDecimal> rates = new HashMap<>();
        rates.put("RUB", new BigDecimal("50.05"));
        currentDayERResponse.setRates(rates);

        previousDayERResponse = new ExchangeRateResponse();
        rates = new HashMap<>();
        rates.put("RUB", new BigDecimal("40.00"));
        previousDayERResponse.setRates(rates);

        giphyResponse = new GiphyResponse();
        List<GIFObject> date = new ArrayList<>();
        GIFObject gifObject = new GIFObject();
        gifObject.setEmbed_url("Some URL");
        date.add(gifObject);
        giphyResponse.setData(date);

        testDisplayedData = new DisplayedData(curCode, currentDayERResponse, previousDayERResponse, giphyResponse);

    }

    @Test
    void getDisplayedData() throws Exception {

        Mockito.when(openExchangeService.getLatestRate(any(String.class))).thenReturn(currentDayERResponse);
        Mockito.when(openExchangeService.getHistoricalRate(any(String.class), any(String.class))).thenReturn(previousDayERResponse);
        Mockito.when(giphyService.getGiphyResponse(any(String.class))).thenReturn(giphyResponse);

        DisplayedData displayedData = gifGettingService.getDisplayedData(curCode);

        assertEquals(displayedData.getUrl(), testDisplayedData.getUrl());
        assertEquals(displayedData.getCurrency(), testDisplayedData.getCurrency());
        assertEquals(displayedData.getComparedCurrency(), testDisplayedData.getComparedCurrency());
        assertEquals(displayedData.getCurrentDayCurrencyRate(), testDisplayedData.getCurrentDayCurrencyRate());
        assertEquals(displayedData.getPreviousDayCurrencyRate(), testDisplayedData.getPreviousDayCurrencyRate());

    }
}