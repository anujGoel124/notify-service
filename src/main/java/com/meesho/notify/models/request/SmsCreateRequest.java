package com.meesho.notify.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmsCreateRequest {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("message")
    private String message;


}
