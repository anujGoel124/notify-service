package com.meesho.notify.dao.cache.redis;

import com.meesho.notify.dao.cache.BlacklistPhoneNumberCache;
import com.meesho.notify.exception.BadRequestException;
import com.meesho.notify.models.request.BlacklistPhoneNumAddRequest;
import com.meesho.notify.models.response.BlacklistListResponse;
import com.meesho.notify.models.response.BlacklistResponse;
import com.meesho.notify.service.implementation.PhoneNumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BlacklistPhoneNumberRedisImpl implements BlacklistPhoneNumberCache {

    @Autowired
    private final RedisTemplate<String, Object> redisTemplate;
    private final HashOperations<String, String, String> hashOperations;

    public static final String KEY = "notification:redis:%s";

    public BlacklistPhoneNumberRedisImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    public BlacklistResponse addPhoneNumber(BlacklistPhoneNumAddRequest blacklistPhoneNumAddRequest) {
        int i = 0;
        int f = 0;
        while (i < blacklistPhoneNumAddRequest.getPhoneNumber().size()) {
            if (PhoneNumberUtil.isValidPhoneNumber(blacklistPhoneNumAddRequest.getPhoneNumber().get(i))) {
                hashOperations.put(KEY, blacklistPhoneNumAddRequest.getPhoneNumber().get(i), blacklistPhoneNumAddRequest.getPhoneNumber().get(i));
                f = 1;
            }
            i++;
        }
        if (f == 1) {
            return BlacklistResponse.builder().data("Successfully blacklisted").build();
        } else {
            log.error("INVALID REQUEST");
            throw new BadRequestException("INVALID REQUEST");
        }
    }

    public boolean isBlacklisted(String num) {
        return hashOperations.hasKey(KEY, num);
    }

    public BlacklistListResponse findAll() {
        List<String> list = new ArrayList<String>(hashOperations.entries(KEY).values());
        return BlacklistListResponse.builder().data(list).build();
    }

    public BlacklistResponse delete(String num) {
        if (PhoneNumberUtil.isValidPhoneNumber(num)) {
            hashOperations.delete(KEY, num);
            return BlacklistResponse.builder().data("Successfully Removed The Blacklisted Number").build();
        } else {
            log.error("Number needs to be Blacklisted and Valid but it is not");
            throw new BadRequestException("Number needs to be Blacklisted and Valid but it is not");
        }
    }

}
