package com.meesho.notify.entity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Builder
public class Sms {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    @JsonProperty("id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    @Column(nullable = false)
    @JsonProperty("phone_number")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String phoneNumber;

    @Column(nullable = false)
    @JsonProperty("message")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonProperty("status")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer status;

    @JsonProperty("failure_code")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private  String failureCode;

    @JsonProperty("failure_comments")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String failureComments;

    @CreationTimestamp
    @JsonProperty("create_date_time")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date createDateTime;

    @UpdateTimestamp
    @JsonProperty("update_date_time")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date updateDateTime;
}

