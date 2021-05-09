package com.meesho.notify.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meesho.notify.models.request.ExternalApiSmsCreateRequest.ImiSmsRequest;
import com.meesho.notify.models.response.ExternalApiResponse;
import com.meesho.notify.service.SmsSender;
import org.apache.catalina.connector.Response;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ImiSmsSender implements SmsSender {
    public boolean sendSms(ImiSmsRequest finalRequest) throws JsonProcessingException {
        String url = "https://api.imiconnect.in/resources/v1/messaging";
        String key = "93ceffda-5941-11ea-9da9-025282c394f2";
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(url)
                .defaultHeader("key", key)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();

                String apiResponse=(restTemplate.postForObject(url, finalRequest, String.class));
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(apiResponse);
        JsonNode transId = rootNode.at("/response/transid");

        return transId.toString().length() <= 0;
    }
}