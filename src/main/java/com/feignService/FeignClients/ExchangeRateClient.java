package com.feignService.FeignClients;

import com.feignService.Responses.ExchangeRateResponses.Currency;
import com.feignService.Responses.ExchangeRateResponses.ExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

//https://openexchangerates.org/api/latest.json?app_id=7f64f89b9a4045fc9c91a5d930c28d25&base=USD&symbols=RUB
@FeignClient(url= "${openexchangerates.url}" , name = "exchangeRatesClient")
public interface ExchangeRateClient {

    @GetMapping("/latest.json")
    ExchangeRateResponse getLatestRate(@RequestParam("app_id") String appId, @RequestParam("base") String base,
                                       @RequestParam("symbols") String comparedCurrency);

    @GetMapping("/historical/{date}.json")
    ExchangeRateResponse getHistoricalRate(@PathVariable("date") String date, @RequestParam("app_id") String appId,
                             @RequestParam("base") String base, @RequestParam("symbols") String comparedCurrency);

    @GetMapping("/currencies.json")
    Map<String, String> getCurrencies();

}
