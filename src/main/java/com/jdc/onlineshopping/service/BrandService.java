package com.jdc.onlineshopping.service;

import com.jdc.onlineshopping.domain.User;
import com.jdc.onlineshopping.web.rest.dto.BrandDTO;
import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;

import java.util.List;

public interface BrandService {

    ResponseDTO create(BrandDTO brandDTO, String requestId);

    ResponseDTO update(BrandDTO dto, String requestId);

    ResponseDTO getList(String requestId, int page, int limit);

    ResponseDTO detail(Long id, String requestId);

    ResponseDTO createMultiple(List<BrandDTO> brandDTOS, String requestId);
}
