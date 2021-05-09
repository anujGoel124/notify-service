package com.meesho.notify.dao.repository.implementation;

import com.meesho.notify.dao.repository.BlacklistPhoneNumberRepository;
import com.meesho.notify.entity.BlacklistedPhoneNumber;
import com.meesho.notify.models.request.BlacklistPhoneNumAddRequest;
import com.meesho.notify.models.response.BlacklistResponse;
import com.meesho.notify.repository.BlacklistedPhoneNumberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BlacklistPhoneNumberRepoImpl implements BlacklistPhoneNumberRepository {

    @Autowired
    private BlacklistedPhoneNumberRepo blacklistedPhoneNumberRepo;

    @Override
    public void addPhoneNumber(BlacklistPhoneNumAddRequest blacklistPhoneNumAddRequest) {
        int i=0;
        while(i<blacklistPhoneNumAddRequest.getPhoneNumber().size()) {
            blacklistedPhoneNumberRepo.save(BlacklistedPhoneNumber.builder().phoneNumber(blacklistPhoneNumAddRequest.getPhoneNumber().get(i)).build());
            i++;
        }
    }
    public void delete(String num) {
        blacklistedPhoneNumberRepo.deleteByPhoneNumber(num);
    }
}
