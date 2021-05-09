package com.meesho.notify.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DateInput {
    private String startDate;
    private String endDate;
    private int offset;
    @Min(value=1,message = "min limit should be 1")
    @Max(value=50,message = "max limit should be 50")
    private int limit;
}
