package com.feignService.Responses.GiphyResponses;

import lombok.Data;

@Data
public class GIFObject {

    private String type;
    private String id;
    private String url;
    private String embed_url;

}
