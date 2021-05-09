package com.meesho.notify.repository;

import com.meesho.notify.entity.SmsSearchData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ESRepository extends ElasticsearchRepository<SmsSearchData,String> {
    Page<SmsSearchData> findAllByCreatedAtBetween(long startEpoch, long endEpoch, Pageable pageable);
}
