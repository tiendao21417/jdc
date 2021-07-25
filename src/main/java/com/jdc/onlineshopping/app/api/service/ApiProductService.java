package com.jdc.onlineshopping.app.api.service;

import com.jdc.onlineshopping.app.api.web.rest.dto.FindProductDTO;
import com.jdc.onlineshopping.domain.User;
import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;

public interface ApiProductService {

    ResponseDTO find(FindProductDTO findProductDTO, User user, String requestId);
}
