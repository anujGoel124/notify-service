package com.meesho.notify.models.response.smsByIdResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmsByIdResponse {
    private SmsDataResponse data;
}
