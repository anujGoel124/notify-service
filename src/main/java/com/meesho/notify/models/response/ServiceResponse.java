package com.meesho.notify.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ServiceResponse<T> {

    @JsonProperty("data")
    private T data;

    @JsonProperty("error")
    private Error error;

    public ServiceResponse(T data) {
        this.data = data;
    }
    public ServiceResponse(Error error){
        this.error=error;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Builder
    public static class Error{

        @JsonProperty("code")
        private String code;

        @JsonProperty("message")
        private String message;
    }


}
