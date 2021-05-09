package com.meesho.notify.dao;

import com.meesho.notify.elasticsearch.ElasticSearchService;
import com.meesho.notify.entity.Sms;
import com.meesho.notify.entity.SmsSearchData;
import com.meesho.notify.enums.SmsSendStatus;
import com.meesho.notify.exception.BadRequestException;
import com.meesho.notify.exception.NotFoundException;
import com.meesho.notify.models.DateInput;
import com.meesho.notify.models.request.SmsCreateEvent;
import com.meesho.notify.models.request.SearchRequest;
import com.meesho.notify.models.request.SmsCreateRequest;
import com.meesho.notify.models.response.PaginatedSearchResponse;
import com.meesho.notify.models.response.SmsResponse;
import com.meesho.notify.models.response.smsByIdResponse.SmsByIdResponse;
import com.meesho.notify.models.response.smsByIdResponse.SmsDataResponse;
import com.meesho.notify.repository.SmsRepository;
import com.meesho.notify.service.BlacklistService;
import com.meesho.notify.service.SmsEventEmitter;
import com.meesho.notify.service.implementation.PhoneNumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SmsDAO {
    @Autowired
    private ElasticSearchService elasticSearchService;
    @Autowired
    private SmsRepository smsRepository;
    @Autowired
    private SmsEventEmitter smsEventEmitter;
    @Autowired
    private BlacklistService blacklistService;

    public SmsResponse sendSms(SmsCreateRequest smsCreateRequest) {
        if (PhoneNumberUtil.isValidPhoneNumber(smsCreateRequest.getPhoneNumber())) {
            final Sms sms = Sms.builder()
                    .phoneNumber(smsCreateRequest.getPhoneNumber())
                    .message(smsCreateRequest.getMessage())
                    .status(SmsSendStatus.CREATED.getCode())
                    .build();
            smsRepository.save(sms);
            log.info("saved sms request- {}", sms);
            SmsResponse smsResponse = SmsResponse.builder().requestId(String.valueOf(sms.getId())).comments("Successfully Sent").build();
            SmsCreateEvent createSmsEvent = SmsCreateEvent.builder().reqId(String.valueOf(sms.getId())).build();
            smsEventEmitter.emit(createSmsEvent);
            return smsResponse;
        } else {
            log.error("phone number is mandatory, request-{}",smsCreateRequest);
            throw new BadRequestException("phone_number is mandatory");
        }
    }

    public List<Sms> getAll() {
        return smsRepository.findAll();
    }

    public SmsByIdResponse findById(Integer id) {
        String msg = "request_id not found";
        Optional<Sms> sms=smsRepository.findById(id);
        if(sms.isPresent()){
            if (blacklistService.isBlacklisted(sms.get().getPhoneNumber())){
                log.error(msg);
                throw new NotFoundException(msg);
            } else {
                SmsDataResponse smsDataResponse = SmsDataResponse.builder().id(id).message(sms.get().getMessage()).phoneNumber(sms.get().getPhoneNumber()).build();
                return SmsByIdResponse.builder().data(smsDataResponse).build();
            }
        } else {
            log.error(msg);
            throw new NotFoundException(msg);
        }
    }

    public void saveSms(Sms sms) {
        smsRepository.save(sms);
    }

    public Optional<Sms> findThroughId(int id) {
        return smsRepository.findById(id);
    }

    public void save(SmsSearchData elasticSmsSearchData) {

        elasticSearchService.save(elasticSmsSearchData);
    }

    public PaginatedSearchResponse findByMessage(SearchRequest searchRequest) {


        return elasticSearchService.findByMessage(searchRequest);
    }

    public PaginatedSearchResponse findByDate(DateInput dateInput) {
        return elasticSearchService.findByDate(dateInput);
    }
}
