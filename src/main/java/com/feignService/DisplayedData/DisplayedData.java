package com.feignService.DisplayedData;

import com.feignService.Responses.ExchangeRateResponses.ExchangeRateResponse;
import com.feignService.Responses.GiphyResponses.GiphyResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class DisplayedData {

    private String url = "";
    private String currency = "";
    private String comparedCurrency = "";
    private BigDecimal previousDayCurrencyRate = new BigDecimal("0");
    private BigDecimal currentDayCurrencyRate = new BigDecimal("0");

    public DisplayedData(String comparedCurrency, ExchangeRateResponse currentDayRate, ExchangeRateResponse previousDayRate, GiphyResponse giphyResponse){

        this.comparedCurrency = comparedCurrency;
        this.url = giphyResponse.getGifUrl();
        this.currency = currentDayRate.getBase();
        this.previousDayCurrencyRate = previousDayRate.getRate();
        this.currentDayCurrencyRate = currentDayRate.getRate();

    }

}
