package com.jdc.onlineshopping.app.api.web.rest.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tiendao on 23/07/2021
 */
@Data
public class CartStoreDTO {

    public CartStoreDTO() {
        items = new ArrayList<>();
    }

    List<CartStoreItem> items;
}
