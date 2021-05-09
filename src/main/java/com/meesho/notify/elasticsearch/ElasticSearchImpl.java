package com.meesho.notify.elasticsearch;
import com.meesho.notify.entity.SmsSearchData;
import com.meesho.notify.exception.BadRequestException;
import com.meesho.notify.models.DateInput;
import com.meesho.notify.models.request.SearchRequest;
import com.meesho.notify.models.response.PaginatedSearchResponse;
import com.meesho.notify.repository.ESRepository;
import com.meesho.notify.service.implementation.DateConverter;
import com.meesho.notify.service.implementation.RequestValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.max;
import static org.elasticsearch.index.query.QueryBuilders.regexpQuery;

@Service
@Slf4j
public class ElasticSearchImpl implements ElasticSearchService {
    @Autowired
    private ESRepository esRepository;
    @Autowired
    private DateConverter dateConverter;
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;
    @Autowired
    private RequestValidation requestValidation;

    @Override
    public void save(SmsSearchData elasticSmsSearchData) {
        esRepository.save(elasticSmsSearchData);
    }

    @Override
    public PaginatedSearchResponse findByMessage(SearchRequest searchRequest) {
        /*if (!requestValidation.validate(searchRequest)) {
            log.error("min and max limit should be 1 and 50 respectively");
            throw new BadRequestException("min limit should be 1 and max limit should be 50 respectively");
        }*/
        int offset = max(0, searchRequest.getOffset());
        int limit = 5;
        if (searchRequest.getLimit() > 0) {
            limit = searchRequest.getLimit();
        }
        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(regexpQuery("message", ".*" + searchRequest.getMessage() + ".*"))
                .withPageable(PageRequest.of(offset, limit))
                .build();
        SearchHits<SmsSearchData> result = elasticsearchOperations.search(searchQuery, SmsSearchData.class, IndexCoordinates.of("data"));
        List<SearchHit<SmsSearchData>> list = result.getSearchHits();
        List<SmsSearchData> response = new ArrayList<>();
        int i = 0;
        while (i < list.size()) {
            response.add(list.get(i).getContent());
            i++;
        }
        return PaginatedSearchResponse.builder().content(response).hasNext(result.getTotalHits() > ((long) offset * limit + list.size())).build();
    }

    @Override
    public PaginatedSearchResponse findByDate(DateInput dateInput) {
        long startEpoch = dateConverter.toEpoch(dateInput.getStartDate());
        long endEpoch = dateConverter.toEpoch(dateInput.getEndDate());
        if (!requestValidation.validate(dateInput) || startEpoch > endEpoch) {
            log.error("min and max limit should be 1 and 50 respectively");
            throw new BadRequestException("min limit should be 1 and max limit should be 50 respectively and also start date should come before end date");
        }
        int offset = max(0, dateInput.getOffset());
        int limit = 5;
        if (dateInput.getLimit() > 0) {
            limit = dateInput.getLimit();
        }
        Page<SmsSearchData> result = esRepository.findAllByCreatedAtBetween(startEpoch, endEpoch, PageRequest.of(offset, limit));
        List<SmsSearchData> response = result.getContent();
        return PaginatedSearchResponse.builder().content(response).hasNext(offset + 2 <= result.getTotalPages()).build();
    }
}
