package com.jdc.onlineshopping.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tiendao on 26/07/2021
 */
@Getter
@Setter
public class ProductAlgoliaDTO extends AlgoliaDTO {

    @JsonProperty("id")
    private long id;

    @JsonProperty("category_id")
    private long categoryId;

    @JsonProperty("category_code")
    private String categoryCode;

    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("brand_id")
    private long brandId;

    @JsonProperty("brand_code")
    private String brandCode;

    @JsonProperty("brand_name")
    private String brandName;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private double price;

    @JsonProperty("remain_amount")
    private int remainAmount;

    @JsonProperty("colour")
    private String colour;

    @JsonProperty("url")
    private String url;

    public static ProductAlgoliaDTO valueOf(ProductDTO productDTO) {

        ProductAlgoliaDTO dto = new ProductAlgoliaDTO();
        dto.setObjectID(String.valueOf(productDTO.getId()));
        dto.setId(productDTO.getId());
        dto.setCategoryId(productDTO.getCategory().getId());
        dto.setCategoryCode(productDTO.getCategory().getCode());
        dto.setCategoryName(productDTO.getCategory().getName());
        dto.setBrandName(productDTO.getBrand().getName());
        dto.setBrandId(productDTO.getBrand().getId());
        dto.setBrandCode(productDTO.getBrand().getCode());
        dto.setName(productDTO.getName());
        dto.setPrice(productDTO.getPrice());
        dto.setRemainAmount(productDTO.getRemainAmount());
        dto.setColour(productDTO.getColour());
        dto.setUrl(productDTO.getUrl());
        return dto;
    }
}
