package com.jdc.onlineshopping.app.api.web.rest.dto;

import lombok.Data;

/**
 * @author tiendao on 23/07/2021
 */
@Data
public class AddRemoveProductToCartDTO {

    private long productId;

    private int amount;
}
