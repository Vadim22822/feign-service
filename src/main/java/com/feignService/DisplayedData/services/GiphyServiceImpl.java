package com.feignService.DisplayedData.services;

import com.feignService.FeignClients.GiphyClient;
import com.feignService.RequestParameters.GiphyRequestParameters;
import com.feignService.Responses.GiphyResponses.GiphyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiphyServiceImpl implements GiphyService {

    private GiphyRequestParameters requestParameters;
    private GiphyClient giphyClient;

    @Autowired
    public void setGiphyRequestParameters(GiphyRequestParameters giphyRequestParameters) {
        this.requestParameters = giphyRequestParameters;
    }

    @Autowired
    public void setGiphyClient(GiphyClient giphyClient) {
        this.giphyClient = giphyClient;
    }

    @Override
    public GiphyResponse getGiphyResponse(String searchedWord) {

        GiphyResponse response = giphyClient.getGiphyResponse(requestParameters.getApiKey(), searchedWord,
                requestParameters.getLimit(), requestParameters.getOffset());

        return response;

    }

}
