package com.jdc.onlineshopping.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private CategoryDTO category;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private double price;

    @JsonProperty("remain_amount")
    private int remainAmount;

    @JsonProperty("brand")
    private BrandDTO brand;

    @JsonProperty("colour")
    private String colour;

    @JsonProperty("url")
    private String url;


    public static ProductDTO valueOf(ProductAlgoliaDTO productAlgoliaDTO) {

        ProductDTO dto = new ProductDTO();
        dto.setObjectID(productAlgoliaDTO.getObjectID());
        dto.setId(productAlgoliaDTO.getId());
        dto.setCategory(new CategoryDTO());
        dto.getCategory().setId(productAlgoliaDTO.getCategoryId());
        dto.getCategory().setCode(productAlgoliaDTO.getCategoryCode());
        dto.getCategory().setName(productAlgoliaDTO.getName());
        dto.setBrand(new BrandDTO());
        dto.getBrand().setId(productAlgoliaDTO.getBrandId());
        dto.getBrand().setCode(productAlgoliaDTO.getBrandCode());
        dto.getBrand().setName(productAlgoliaDTO.getName());
        dto.setName(productAlgoliaDTO.getName());
        dto.setPrice(productAlgoliaDTO.getPrice());
        dto.setRemainAmount(productAlgoliaDTO.getRemainAmount());
        dto.setColour(productAlgoliaDTO.getColour());
        dto.setUrl(productAlgoliaDTO.getUrl());
        return dto;

    }
}
