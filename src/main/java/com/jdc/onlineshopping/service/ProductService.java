package com.jdc.onlineshopping.service;

import com.jdc.onlineshopping.app.ops.web.rest.dto.CreateMultipleProductDTO;
import com.jdc.onlineshopping.app.ops.web.rest.dto.CreateProductDTO;
import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;

public interface ProductService {

    ResponseDTO create(CreateProductDTO dto, String requestId);

    ResponseDTO update(Long id, CreateProductDTO dto, String requestId);

    ResponseDTO getList(String requestId, int page, int limit);

    ResponseDTO detail(Long id, String requestId);

    ResponseDTO multiple(CreateMultipleProductDTO createMultipleProductDTO, String requestId);
}
