package com.jdc.onlineshopping.app.api.service;

import com.jdc.onlineshopping.app.api.web.rest.dto.AddRemoveProductToCartDTO;
import com.jdc.onlineshopping.app.api.web.rest.dto.CartStoreDTO;
import com.jdc.onlineshopping.domain.User;
import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;

public interface CartService {

    ResponseDTO addRemoveToCart(User user, AddRemoveProductToCartDTO dto, String requestId);

    ResponseDTO detail(User user, String requestId);

    CartStoreDTO getCart(User user);

    void clear(User user);
}
