package com.feignService.DisplayedData.services;

import com.feignService.Responses.ExchangeRateResponses.ExchangeRateResponse;

import java.util.Map;

public interface OpenExchangeService {

    ExchangeRateResponse getLatestRate(String curCode) throws Exception;

    ExchangeRateResponse getHistoricalRate(String curCode, String date) throws Exception;

    Map<String, String> getCurrencies();

}
