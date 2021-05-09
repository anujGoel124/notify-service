package com.meesho.notify.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import javax.persistence.Id;
import java.util.Date;

@Document(indexName = "data")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmsSearchData {

    @Id
    private String id;

    private String message;

    private String phoneNumber;

    private Date createdAt;

}
