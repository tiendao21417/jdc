package com.jdc.onlineshopping.app.ops.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author tiendao on 17/07/2021
 */
@Data
public class CreateProductDTO {

    @NotBlank(message = "category not be empty")
    @JsonProperty("category_id")
    private Long categoryId;

    @NotBlank(message = "brand not be empty")
    @JsonProperty("brand_id")
    private Long brandId;

    @NotBlank(message = "name not be empty")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "colour not be empty")
    @JsonProperty("colour")
    private String colour;

    @JsonProperty("url")
    private String url;

    @NotBlank(message = "price not be empty")
    @JsonProperty("price")
    private Double price;
}
