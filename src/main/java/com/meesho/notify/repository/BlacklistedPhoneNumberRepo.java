package com.meesho.notify.repository;

import com.meesho.notify.entity.BlacklistedPhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface BlacklistedPhoneNumberRepo extends JpaRepository<BlacklistedPhoneNumber,Integer> {
    @Transactional
    void deleteByPhoneNumber(String firstName);
}
