package com.meesho.notify.models.response;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExternalApiResponse {

        private String code;
        private String description;
        private String transid;
    }
