package com.jdc.onlineshopping.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author tiendao on 26/07/2021
 */

public class ProductAlgoliaDTO /*extends AlgoliaDTO */{

    @JsonProperty("objectID")
    private String objectID;

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

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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
