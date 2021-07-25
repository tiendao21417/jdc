package com.jdc.onlineshopping.mapper;

import com.jdc.onlineshopping.domain.Brand;
import com.jdc.onlineshopping.web.rest.dto.BrandDTO;
import org.mapstruct.Mapper;

/**
 * @author tiendao on 22/07/2021
 */
@Mapper(componentModel = "spring")
public interface BrandMapper extends EntityMapper<BrandDTO, Brand> {
}
