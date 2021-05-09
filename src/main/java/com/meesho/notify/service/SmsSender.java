package com.meesho.notify.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meesho.notify.models.request.ExternalApiSmsCreateRequest.ImiSmsRequest;

public interface SmsSender {

    boolean sendSms(ImiSmsRequest finalRequest) throws JsonProcessingException;
}
