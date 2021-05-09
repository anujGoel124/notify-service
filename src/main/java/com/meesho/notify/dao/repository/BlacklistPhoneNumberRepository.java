package com.meesho.notify.dao.repository;

import com.meesho.notify.entity.BlacklistedPhoneNumber;
import com.meesho.notify.models.request.BlacklistPhoneNumAddRequest;
import com.meesho.notify.models.response.BlacklistResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface BlacklistPhoneNumberRepository{

    void delete(String firstName);
    void addPhoneNumber(BlacklistPhoneNumAddRequest blacklistPhoneNumAddRequest);

}
