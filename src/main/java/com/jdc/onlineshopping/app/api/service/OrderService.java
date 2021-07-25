package com.jdc.onlineshopping.app.api.service;

import com.jdc.onlineshopping.app.api.web.rest.dto.CreateOrderDTO;
import com.jdc.onlineshopping.domain.User;
import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;

public interface OrderService {
    ResponseDTO getOrder(Long id, User user, String requestId);

    ResponseDTO create(User user, CreateOrderDTO dto, String requestId);
}
