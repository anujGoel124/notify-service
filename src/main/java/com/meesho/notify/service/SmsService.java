package com.meesho.notify.service;

import com.meesho.notify.entity.Sms;
import com.meesho.notify.entity.SmsSearchData;
import com.meesho.notify.models.DateInput;
import com.meesho.notify.models.request.SearchRequest;
import com.meesho.notify.models.request.SmsCreateRequest;
import com.meesho.notify.models.response.PaginatedSearchResponse;
import com.meesho.notify.models.response.SmsResponse;
import com.meesho.notify.models.response.smsByIdResponse.SmsByIdResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SmsService {
    SmsResponse sendSms(SmsCreateRequest smsCreateRequest);

    List<Sms> getAll();

    SmsByIdResponse findById(Integer id);

    PaginatedSearchResponse search(SearchRequest searchRequest);

    PaginatedSearchResponse filter(DateInput dateInput);


}
