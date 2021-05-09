package com.meesho.notify.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class SmsResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("request_id")
    private String requestId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("comments")
    private String comments;
}
