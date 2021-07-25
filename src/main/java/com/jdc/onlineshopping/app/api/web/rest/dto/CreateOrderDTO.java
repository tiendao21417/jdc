package com.jdc.onlineshopping.app.api.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author tiendao on 24/07/2021
 */
@Data
public class CreateOrderDTO {

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("customer_address")
    private String customerAddress;

    @JsonProperty("customer_phone")
    private String customerPhone;

}
