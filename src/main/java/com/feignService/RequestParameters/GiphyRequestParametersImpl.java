package com.feignService.RequestParameters;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Data
public class GiphyRequestParametersImpl implements GiphyRequestParameters{

    @Value("${giphy.api_key}")
    private String apiKey;
    @Value("${giphy.rich}")
    private String searchWordRich;
    @Value("${giphy.broke}")
    private String searchWordBroke;
    @Value("${giphy.limit}")
    private String limit;
    @Value("${giphy.maxOffset}")
    private Integer maxOffset;

    @Override
    public String getOffset() {
        Random random = new Random();
        Integer offset = random.ints(0, maxOffset)
                .findFirst()
                .getAsInt();
        return offset.toString();
    }


}
