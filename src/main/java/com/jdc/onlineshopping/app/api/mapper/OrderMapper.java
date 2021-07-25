package com.jdc.onlineshopping.app.api.mapper;

import com.jdc.onlineshopping.app.api.web.rest.dto.OrderDTO;
import com.jdc.onlineshopping.domain.Brand;
import com.jdc.onlineshopping.domain.Order;
import com.jdc.onlineshopping.mapper.EntityMapper;
import com.jdc.onlineshopping.web.rest.dto.BrandDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper extends EntityMapper<OrderDTO, Order> {
}
