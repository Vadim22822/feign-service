package com.feignService.RequestParameters;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class RateRequestParametersImpl implements RateRequestParameters {

    @Value("${openexchangerates.app_id}")
    private String appId;
    @Value("${openexchangerates.base}")
    private String base;
    @Value("${openexchangerates.comparedCurrency}")
    private String comparedCurrency;

}
