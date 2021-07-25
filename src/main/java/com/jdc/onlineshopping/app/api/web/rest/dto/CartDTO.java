package com.jdc.onlineshopping.app.api.web.rest.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tiendao on 24/07/2021
 */
@Data
public class CartDTO {

    public CartDTO() {
        items = new ArrayList<>();
    }

    List<CartItem> items;
}
