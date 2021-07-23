package com.jdc.onlineshopping.mapper;

import com.jdc.onlineshopping.domain.Brand;
import com.jdc.onlineshopping.domain.Category;
import com.jdc.onlineshopping.web.rest.dto.BrandDTO;
import com.jdc.onlineshopping.web.rest.dto.CategoryDTO;
import org.mapstruct.Mapper;

/**
 * @author tiendao on 22/07/2021
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {
}
