package com.meesho.notify.repository;

import com.meesho.notify.entity.Sms;
import com.meesho.notify.models.response.SmsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepository extends JpaRepository<Sms,Integer> {
}
