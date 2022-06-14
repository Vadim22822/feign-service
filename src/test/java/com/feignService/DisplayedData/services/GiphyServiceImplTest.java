package com.feignService.DisplayedData.services;

import com.feignService.FeignClients.GiphyClient;
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
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringRunner.class)
@SpringBootTest
class GiphyServiceImplTest {

    @MockBean
    private GiphyClient giphyClient;
    private GiphyService giphyService;
    private GiphyResponse testResponse;

    @Autowired
    public void setGiphyService(GiphyService giphyService) {
        this.giphyService = giphyService;
    }

    @BeforeEach
    public void init() {

        testResponse = new GiphyResponse();
        List<GIFObject> date = new ArrayList<>();
        GIFObject gifObject = new GIFObject();
        gifObject.setEmbed_url("Some URL");
        date.add(gifObject);
        testResponse.setData(date);

    }

    @Test
    void getGiphyResponse() {

        Mockito.when(giphyClient.getGiphyResponse(any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(testResponse);
        String anyString = "anyString()";
        GiphyResponse response = giphyService.getGiphyResponse(anyString);

        assertEquals(response.getGifUrl(), testResponse.getGifUrl());

    }

}