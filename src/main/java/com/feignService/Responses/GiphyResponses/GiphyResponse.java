package com.feignService.Responses.GiphyResponses;

import lombok.Data;

import java.util.List;

@Data
public class GiphyResponse {

    private List<GIFObject> data;

    public String getGifUrl() {

        for (GIFObject gifObject: data) {
            return gifObject.getEmbed_url();
        }

        return "";

    }

}
