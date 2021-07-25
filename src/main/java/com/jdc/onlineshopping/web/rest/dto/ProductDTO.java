package com.jdc.onlineshopping.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jdc.onlineshopping.domain.Brand;
import com.jdc.onlineshopping.domain.Category;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tiendao on 22/07/2021
 */
@Getter
@Setter
public class ProductDTO extends AlgoliaDTO {

    @JsonProperty("id")
    private long id;

    @JsonProperty("category")
    private Category category;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private double price;

    @JsonProperty("remain_amount")
    private int remainAmount;

    @JsonProperty("brand")
    private Brand brand;

    @JsonProperty("colour")
    private String colour;

    @JsonProperty("url")
    private String url;
}
