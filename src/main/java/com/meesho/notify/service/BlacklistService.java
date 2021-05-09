package com.meesho.notify.service;

import com.meesho.notify.models.request.BlacklistPhoneNumAddRequest;
import com.meesho.notify.models.response.BlacklistListResponse;
import com.meesho.notify.models.response.BlacklistResponse;
import org.springframework.http.ResponseEntity;

public interface BlacklistService {
    public BlacklistResponse addPhoneNumber(BlacklistPhoneNumAddRequest blacklistPhoneNumAddRequest);
    public BlacklistResponse deleteFromBlacklist(String phoneNumber);
    public BlacklistListResponse findAll();
    public boolean isBlacklisted(String phoneNumber);
}
