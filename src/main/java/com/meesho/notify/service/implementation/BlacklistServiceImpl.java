package com.meesho.notify.service.implementation;

import com.meesho.notify.dao.BlacklistPhoneNumberDAO;
import com.meesho.notify.models.request.BlacklistPhoneNumAddRequest;
import com.meesho.notify.models.response.BlacklistListResponse;
import com.meesho.notify.models.response.BlacklistResponse;
import com.meesho.notify.service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlacklistServiceImpl implements BlacklistService {

    @Autowired
    private BlacklistPhoneNumberDAO blacklistPhoneNumberDAO;

    public BlacklistResponse addPhoneNumber(BlacklistPhoneNumAddRequest blacklistPhoneNumAddRequest) {
        return blacklistPhoneNumberDAO.addPhoneNumber(blacklistPhoneNumAddRequest);
    }

    public BlacklistResponse deleteFromBlacklist(String phoneNumber) {

        return blacklistPhoneNumberDAO.delete(phoneNumber);
    }

    public BlacklistListResponse findAll(){
        return blacklistPhoneNumberDAO.findAll();
    }

    public boolean isBlacklisted(String phoneNumber)
    {
        return blacklistPhoneNumberDAO.isBlacklisted(phoneNumber);
    }

}
