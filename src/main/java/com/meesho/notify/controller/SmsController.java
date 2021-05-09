package com.meesho.notify.controller;
import com.meesho.notify.entity.Sms;
import com.meesho.notify.models.DateInput;
import com.meesho.notify.models.request.SearchRequest;
import com.meesho.notify.models.request.SmsCreateRequest;
import com.meesho.notify.models.response.PaginatedSearchResponse;
import com.meesho.notify.models.response.SmsResponse;
import com.meesho.notify.models.response.SmsStatusResponse;
import com.meesho.notify.models.response.smsByIdResponse.SmsByIdResponse;
import com.meesho.notify.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Slf4j
@Controller
@RequestMapping("/v1/sms")
public class SmsController {
    @Autowired
    private SmsService smsService;

    @PostMapping(path = "/send")
    public @ResponseBody
    SmsStatusResponse sendSms(@RequestBody SmsCreateRequest smsCreateRequest) {
        log.debug("send sms create request:{}", smsCreateRequest);
        SmsResponse smsResponse = smsService.sendSms(smsCreateRequest);
        return SmsStatusResponse.builder().data(smsResponse).build();
    }

    @GetMapping(path = "{id}")
    public @ResponseBody
    SmsByIdResponse findById(@PathVariable Integer id) {
        log.debug("find sms by id request:{}", id);
        return smsService.findById(id);
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    List<Sms> getSms() {
        log.debug("getting all the sms");
        return smsService.getAll();
    }

    @GetMapping("/filter")
    public @ResponseBody
    PaginatedSearchResponse filter(@RequestBody DateInput dateInput) {
        log.debug("filtering the sms based on date input request:{}", dateInput);
        return smsService.filter(dateInput);
    }

    @GetMapping("/search")
    public @ResponseBody
    PaginatedSearchResponse search(@RequestBody @Valid SearchRequest searchRequest) {
        log.debug("find sms by search request:{}", searchRequest);
        return smsService.search(searchRequest);
    }
}
