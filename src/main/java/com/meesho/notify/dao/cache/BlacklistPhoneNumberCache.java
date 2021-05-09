package com.meesho.notify.dao.cache;

import com.meesho.notify.models.request.BlacklistPhoneNumAddRequest;
import com.meesho.notify.models.response.BlacklistListResponse;
import com.meesho.notify.models.response.BlacklistResponse;

public interface BlacklistPhoneNumberCache {

     BlacklistResponse addPhoneNumber(BlacklistPhoneNumAddRequest blacklistPhoneNumAddRequest);
     boolean isBlacklisted(String num);
     BlacklistResponse delete(String num);
     BlacklistListResponse findAll();
}
