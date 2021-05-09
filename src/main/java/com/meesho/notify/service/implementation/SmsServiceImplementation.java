package com.meesho.notify.service.implementation;

import com.meesho.notify.dao.SmsDAO;
import com.meesho.notify.entity.Sms;
import com.meesho.notify.entity.SmsSearchData;
import com.meesho.notify.models.DateInput;
import com.meesho.notify.models.request.SearchRequest;
import com.meesho.notify.models.request.SmsCreateRequest;
import com.meesho.notify.models.response.PaginatedSearchResponse;
import com.meesho.notify.models.response.SmsResponse;
import com.meesho.notify.models.response.smsByIdResponse.SmsByIdResponse;
import com.meesho.notify.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j
@Service
public class SmsServiceImplementation implements SmsService {

    @Autowired
    private SmsDAO smsDAO;

    public SmsResponse sendSms(SmsCreateRequest smsCreateRequest) {
        return smsDAO.sendSms(smsCreateRequest);
    }

    public List<Sms> getAll() {
        return smsDAO.getAll();
    }

    public SmsByIdResponse findById(Integer id) {
        return smsDAO.findById(id);
    }

    public PaginatedSearchResponse search(SearchRequest searchRequest) {
        return smsDAO.findByMessage(searchRequest);
    }

    public PaginatedSearchResponse filter(DateInput dateInput) {
        return smsDAO.findByDate(dateInput);
    }


}
