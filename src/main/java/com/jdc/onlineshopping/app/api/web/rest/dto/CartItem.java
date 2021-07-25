package com.jdc.onlineshopping.app.api.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author tiendao on 24/07/2021
 */
@Data
public class CartItem {

    @JsonProperty("product_id")
    private long productId;

    @JsonProperty("brand_code")
    private String brandCode;

    @JsonProperty("brand_name")
    private String brandName;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("url")
    private String url;

    @JsonProperty("unit_price")
    private double unitPrice;

    @JsonProperty("amount")
    private int amount;

    @JsonProperty("category_code")
    private String categoryCode;

    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("colour")
    private String colour;
}
