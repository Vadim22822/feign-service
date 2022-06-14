package com.feignService.controllers;

import com.feignService.DisplayedData.DisplayedData;
import com.feignService.DisplayedData.services.GIFGettingService;
import com.feignService.DisplayedData.services.OpenExchangeService;
import com.feignService.Responses.ExchangeRateResponses.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping
public class ExchangeRateController {

    private GIFGettingService gifReceivingService;
    private OpenExchangeService openExchangeService;

    @Autowired
    public void setGifReceivingService(GIFGettingService gifReceivingService) {
        this.gifReceivingService = gifReceivingService;
    }

    @Autowired
    public void setOpenExchangeService(OpenExchangeService openExchangeService) {
        this.openExchangeService = openExchangeService;
    }

    @GetMapping("/ExchangeRate/{curCode}")
    public String exchangeRate(@PathVariable String curCode, Model model) {

        DisplayedData displayedData = null;
        try {
            displayedData = gifReceivingService.getDisplayedData(curCode);
        } catch (Exception e) {
            displayedData = new DisplayedData();
            displayedData.setCurrency("Error :" + e.getMessage());
            e.printStackTrace();
        }

        //answer = "https://giphy.com/embed/l2Sq4UXqFtdDJZpwk";
        fillModel(model, displayedData);

        return "exchange-rate";

    }

    @GetMapping("/CurrenciesList")
    public String currenciesList(Model model) {

        Map<String, String> currenciesMap = openExchangeService.getCurrencies();
        List<Currency> currencies = getCurrencies(currenciesMap);

        model.addAttribute("currencies", currencies);
        return "currencies-list";

    }

    private List<Currency> getCurrencies(Map<String, String> currenciesMap) {

        List<Currency> currenciesList = new ArrayList<>(currenciesMap.size());
        Set<Map.Entry<String, String>> mapEntry = currenciesMap.entrySet();

        for (Map.Entry<String, String> keyValue: mapEntry) {
            Currency currency = new Currency();
            currency.setCurCode(keyValue.getKey());
            currency.setDescription(keyValue.getValue());
            currenciesList.add(currency);
        }

        return currenciesList;

    }

    private void fillModel(Model model, DisplayedData displayedData) {

        model.addAttribute("currency", "Текущая валюта: " + displayedData.getCurrency());
        model.addAttribute("comparedCurrency", "Сравниваемая валюта: " + displayedData.getComparedCurrency());
        model.addAttribute("previousDayCurrencyRate", "Курс предыдущего дня: " + displayedData.getPreviousDayCurrencyRate());
        model.addAttribute("currentDayCurrencyRate", "Курс текущего дня: " + displayedData.getCurrentDayCurrencyRate());
        model.addAttribute("embebUrl", displayedData.getUrl());

    }

}
