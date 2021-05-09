package com.meesho.notify.models.request.ExternalApiSmsCreateRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Channels {
    private Text sms;
}
