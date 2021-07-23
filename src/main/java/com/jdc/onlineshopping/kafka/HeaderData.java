package com.jdc.onlineshopping.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author tiendao on 22/07/2021
 */
@Data
public class HeaderData {

    @JsonProperty("key")
    private String key;

    @JsonProperty("request_id")
    private String requestId;
}
