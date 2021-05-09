package com.meesho.notify.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meesho.notify.entity.SmsSearchData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedSearchResponse {
    private List<SmsSearchData> content;
    @JsonProperty("has_next")
    private boolean hasNext;
}

