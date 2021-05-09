package com.meesho.notify.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {

    @NotEmpty(message = "message cannot be empty")
    private String message;
    private int offset;
   @Min(value = 1,message = "min limit should be 1")
   @Max(value = 50,message = "max limit should be 50")
    private int limit;
}
