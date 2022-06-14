package com.feignService.Responses.GiphyResponses;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GiphyResponseTest {

    @Test
    void getGifUrl() {

        List<GIFObject> data = new ArrayList<>();
        GIFObject gifObject = new GIFObject();
        gifObject.setEmbed_url("Some URL");
        data.add(gifObject);
        GiphyResponse giphyResponse = new GiphyResponse();
        giphyResponse.setData(data);

        assertEquals(giphyResponse.getGifUrl(), "Some URL");

    }
}