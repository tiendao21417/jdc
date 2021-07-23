package com.jdc.onlineshopping.app.api.web.rest.dto;

import lombok.Data;

import java.util.List;

/**
 * @author tiendao on 22/07/2021
 */
@Data
public class FindProductDTO {

    private String name;
    private List<Long> categoryIds;
    private List<Long> brandIds;
    private List<String> colours;
    private Double priceMin;
    private Double priceMax;
    private long page;
    private long limit;
}
