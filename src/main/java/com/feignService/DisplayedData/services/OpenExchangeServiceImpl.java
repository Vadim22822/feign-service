package com.feignService.DisplayedData.services;

import com.feignService.FeignClients.ExchangeRateClient;
import com.feignService.RequestParameters.RateRequestParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.feignService.Responses.ExchangeRateResponses.ExchangeRateResponse;

import java.util.Map;

@Service
public class OpenExchangeServiceImpl implements OpenExchangeService {

    private ExchangeRateClient exchangeRateClient;
    private RateRequestParameters rateRequestParameters;

    @Autowired
    public void setExchangeRateClient(ExchangeRateClient exchangeRateClient) {
        this.exchangeRateClient = exchangeRateClient;
    }

    @Autowired
    public void setRequestParameters(RateRequestParameters rateRequestParameters) {
        this.rateRequestParameters = rateRequestParameters;
    }

    @Override
    public ExchangeRateResponse getLatestRate(String curCode) throws Exception {

        ExchangeRateResponse response =
                exchangeRateClient.getLatestRate(rateRequestParameters.getAppId(), rateRequestParameters.getBase(),
                        curCode);

        checkError(response);

        return response;

    }

    @Override
    public ExchangeRateResponse getHistoricalRate(String curCode, String date) throws Exception {

        ExchangeRateResponse response = exchangeRateClient.getHistoricalRate(date, rateRequestParameters.getAppId(),
                rateRequestParameters.getBase(), curCode);

        checkError(response);

        return response;

    }

    @Override
    public Map<String, String> getCurrencies() {
        return exchangeRateClient.getCurrencies();
    }

    private void checkError(ExchangeRateResponse response) throws Exception {

        if (response.isError()) {
            throw new Exception(response.getDescription());
        }

    }

}
