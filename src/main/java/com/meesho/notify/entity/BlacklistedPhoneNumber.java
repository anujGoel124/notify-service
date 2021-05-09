package com.meesho.notify.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Builder
public class BlacklistedPhoneNumber {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    @JsonProperty("id")
    private Integer id;

    @Column(nullable = false)
    @JsonProperty("phone_number")
    private String phoneNumber;
}
