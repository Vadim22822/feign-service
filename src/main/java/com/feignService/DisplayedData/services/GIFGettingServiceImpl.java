package com.feignService.DisplayedData.services;

import com.feignService.DisplayedData.DisplayedData;
import com.feignService.RequestParameters.GiphyRequestParameters;
import com.feignService.RequestParameters.RateRequestParameters;
import com.feignService.Responses.ExchangeRateResponses.ExchangeRateResponse;
import com.feignService.Responses.GiphyResponses.GiphyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class GIFGettingServiceImpl implements GIFGettingService {

    private OpenExchangeService openExchangeService;
    private GiphyService giphyService;
    private GiphyRequestParameters giphyRequestParameters;
    private RateRequestParameters rateRequestParameters;

    @Autowired
    public void setExchangeRateClient(OpenExchangeService openExchangeService) {
        this.openExchangeService = openExchangeService;
    }

    @Autowired
    public void setGiphyService(GiphyService giphyService) {
        this.giphyService = giphyService;
    }

    @Autowired
    public void setGiphyRequestParameters(GiphyRequestParameters giphyRequestParameters) {
        this.giphyRequestParameters = giphyRequestParameters;
    }

    @Autowired
    public void setRateRequestParameters(RateRequestParameters rateRequestParameters) {
        this.rateRequestParameters = rateRequestParameters;
    }

    @Override
    public DisplayedData getDisplayedData(String curCode) throws Exception {

        if (curCode == null || curCode.equals("")) {
            throw new Exception("Invalid curCode value");
        }

        ExchangeRateResponse response = openExchangeService.getLatestRate(curCode);

        String dateFormat = getFormattedDateOfPreviousDay();

        ExchangeRateResponse previousDateResponse = openExchangeService.getHistoricalRate(curCode, dateFormat);

        String searchedWord = getSearchedWord(response, previousDateResponse);
        GiphyResponse giphyResponse = giphyService.getGiphyResponse(searchedWord);

        return new DisplayedData(curCode, response, previousDateResponse, giphyResponse);

    }

    private String getFormattedDateOfPreviousDay() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate previousDay = LocalDate.now().minusDays(1);
        return dtf.format(previousDay);

    }

    private String getSearchedWord(ExchangeRateResponse response, ExchangeRateResponse previousDateResponse) throws Exception {

        String searchedWord = "";
        boolean isRichGif = response.compareRates(previousDateResponse) > 0;
        if (isRichGif) {
            searchedWord = giphyRequestParameters.getSearchWordRich();
        }
        else {
            searchedWord = giphyRequestParameters.getSearchWordBroke();
        }

        return searchedWord;

    }

}
