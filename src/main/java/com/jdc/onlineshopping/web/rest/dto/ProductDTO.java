package com.jdc.onlineshopping.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author tiendao on 22/07/2021
 */
// @Getter
// @Setter
public class ProductDTO /* extends AlgoliaDTO */{

    @JsonProperty("objectID")
    private String objectID;

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

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(int remainAmount) {
        this.remainAmount = remainAmount;
    }

    public BrandDTO getBrand() {
        return brand;
    }

    public void setBrand(BrandDTO brand) {
        this.brand = brand;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
