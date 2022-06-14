package com.feignService.DisplayedData.services;

import com.feignService.Responses.GiphyResponses.GiphyResponse;

public interface GiphyService {

    GiphyResponse getGiphyResponse(String searchedWord);

}
