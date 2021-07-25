package com.jdc.onlineshopping.app.ops.web.rest.dto;

import lombok.Data;

import java.util.List;

/**
 * @author tiendao on 26/07/2021
 */
@Data
public class CreateMultipleProductDTO {

    List<CreateProductDTO> items;
}
