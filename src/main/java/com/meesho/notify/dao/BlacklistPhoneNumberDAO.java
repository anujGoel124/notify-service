package com.meesho.notify.dao;

import com.meesho.notify.dao.cache.BlacklistPhoneNumberCache;
import com.meesho.notify.dao.repository.BlacklistPhoneNumberRepository;
import com.meesho.notify.models.request.BlacklistPhoneNumAddRequest;
import com.meesho.notify.models.response.BlacklistListResponse;
import com.meesho.notify.models.response.BlacklistResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BlacklistPhoneNumberDAO {
    @Autowired
    private BlacklistPhoneNumberCache blacklistPhoneNumberCache;
    @Autowired
    private BlacklistPhoneNumberRepository blacklistPhoneNumberRepository;

    public BlacklistResponse addPhoneNumber(BlacklistPhoneNumAddRequest blacklistPhoneNumAddRequest) {
        blacklistPhoneNumberRepository.addPhoneNumber(blacklistPhoneNumAddRequest);
        return blacklistPhoneNumberCache.addPhoneNumber(blacklistPhoneNumAddRequest);
    }

    public boolean isBlacklisted(String num) {
        return blacklistPhoneNumberCache.isBlacklisted(num);
    }

    public BlacklistResponse delete(String num) {
        blacklistPhoneNumberRepository.delete(num);
        return blacklistPhoneNumberCache.delete(num);
    }

    public BlacklistListResponse findAll() {
        return blacklistPhoneNumberCache.findAll();
    }

}
