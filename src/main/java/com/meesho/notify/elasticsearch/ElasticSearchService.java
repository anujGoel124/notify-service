package com.meesho.notify.elasticsearch;

import com.meesho.notify.entity.SmsSearchData;
import com.meesho.notify.models.DateInput;
import com.meesho.notify.models.request.SearchRequest;
import com.meesho.notify.models.response.PaginatedSearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public interface ElasticSearchService {
    void save(SmsSearchData elasticSmsSearchData);
    PaginatedSearchResponse findByMessage(SearchRequest searchRequest);
    PaginatedSearchResponse findByDate(DateInput dateInput);
}
