package com.feignService.FeignClients;

import com.feignService.Responses.GiphyResponses.GiphyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//https://api.giphy.com/v1/gifs/search?api_key=7hBwu1VJjlxwe10nwp3QYf22lYTFrTcq&q=rich&limit=1&offset=2
@FeignClient(url= "${giphy.url}" , name = "giphyClient")
public interface GiphyClient {

    @GetMapping("/search")
    GiphyResponse getGiphyResponse(@RequestParam("api_key") String apiKey, @RequestParam("q") String searchWord,
                         @RequestParam("limit") String limit, @RequestParam("offset") String offset);

}
