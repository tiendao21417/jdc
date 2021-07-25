package com.jdc.onlineshopping.mapper;

import com.jdc.onlineshopping.domain.Product;
import com.jdc.onlineshopping.web.rest.dto.ProductDTO;
import org.mapstruct.Mapper;

/**
 * @author tiendao on 22/07/2021
 */
@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {
}
