package com.feignService.Responses.ExchangeRateResponses;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@Data
public class ExchangeRateResponse {

    private String disclaimer;
    private String license;
    private Long timestamp;
    private String base;
    private Map<String, BigDecimal> rates;

    // Error fields
    private boolean error;
    private Integer status;
    private String message;
    private String description;

    public BigDecimal getRate() {

        return getValueFromMap(Map.Entry::getValue, new BigDecimal("0"));

    }

    public String getComparedCurrency() {

        return getValueFromMap(Map.Entry::getKey, "");

    }

    private <T> T getValueFromMap(Function<Map.Entry<String, BigDecimal>, T> function, T defaultValue) {

        T value = defaultValue;
        if (this.rates == null || this.rates.isEmpty()) {
            return value;
        }

        Set<Map.Entry<String, BigDecimal>> entrySet = this.rates.entrySet();
        for (Map.Entry<String, BigDecimal> keyValue: entrySet) {
            value = function.apply(keyValue);
            return value;
        }

        return value;

    }

    public boolean isError() {

        return this.error;

    }

    public int compareRates(ExchangeRateResponse rateResponse) throws Exception {

        if (this == rateResponse) {
            return 0;
        }

        BigDecimal currentRate = getRate();
        BigDecimal previousRate = rateResponse.getRate();

        if (currentRate == null || previousRate == null) {
            throw new Exception("Variables currentRate and previousRate can't be null");
        }

        return currentRate.compareTo(previousRate);

    }

}
