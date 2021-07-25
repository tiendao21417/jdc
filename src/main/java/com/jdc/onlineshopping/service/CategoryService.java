package com.jdc.onlineshopping.service;

import com.jdc.onlineshopping.web.rest.dto.CategoryDTO;
import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;

import java.util.List;

/**
 * @author tiendao on 22/07/2021
 */
public interface CategoryService {

    ResponseDTO detail(Long id, String requestId);

    ResponseDTO getList(String requestId, int page, int limit);

    ResponseDTO update(CategoryDTO dto, String requestId);

    ResponseDTO create(CategoryDTO dto, String requestId);

    ResponseDTO createMultiple(List<CategoryDTO> categoryDTOS, String requestId);
}
