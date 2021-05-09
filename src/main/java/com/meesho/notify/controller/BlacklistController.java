package com.meesho.notify.controller;

import com.meesho.notify.models.request.BlacklistPhoneNumDelRequest;
import com.meesho.notify.models.request.BlacklistPhoneNumAddRequest;
import com.meesho.notify.models.response.BlacklistListResponse;
import com.meesho.notify.models.response.BlacklistResponse;
import com.meesho.notify.service.BlacklistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping(path = "/v1/blacklist")
public class BlacklistController {

    @Autowired
    private BlacklistService blacklistService;

    @PostMapping(path = "")
    public @ResponseBody
    BlacklistResponse addToBlacklist(@RequestBody BlacklistPhoneNumAddRequest blacklistPhoneNumAddRequest) {
        log.debug("add blacklist phone number request:{}", blacklistPhoneNumAddRequest);
        return blacklistService.addPhoneNumber(blacklistPhoneNumAddRequest);
    }

    @PostMapping(path = "/remove")
    public @ResponseBody
    BlacklistResponse deleteFromBlacklist(@RequestBody BlacklistPhoneNumDelRequest blacklistPhoneNumDelRequest) {
        log.debug("delete blacklist phone number request:{}", blacklistPhoneNumDelRequest);
        return blacklistService.deleteFromBlacklist(blacklistPhoneNumDelRequest.getPhoneNumber());
    }

    @GetMapping(path = "")
    public @ResponseBody
    BlacklistListResponse findAll() {
        log.debug("get all blacklisted phone numbers request");
        return blacklistService.findAll();
    }

}
