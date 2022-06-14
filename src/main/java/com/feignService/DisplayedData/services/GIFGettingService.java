package com.feignService.DisplayedData.services;

import com.feignService.DisplayedData.DisplayedData;

public interface GIFGettingService {

    DisplayedData getDisplayedData(String curCode) throws Exception;

}
