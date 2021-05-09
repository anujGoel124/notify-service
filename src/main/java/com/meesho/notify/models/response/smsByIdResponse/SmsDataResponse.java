package com.meesho.notify.models.response.smsByIdResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmsDataResponse {
    private Integer id;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String message;
}
