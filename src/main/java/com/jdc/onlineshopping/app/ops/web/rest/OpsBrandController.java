package com.jdc.onlineshopping.app.ops.web.rest;

import com.jdc.onlineshopping.constant.CPagging;
import com.jdc.onlineshopping.constant.CRequestAttribute;
import com.jdc.onlineshopping.app.ops.web.rest.dto.CreateBrandDTO;
import com.jdc.onlineshopping.service.BrandService;
import com.jdc.onlineshopping.web.rest.dto.BrandDTO;
import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author tiendao on 18/07/2021
 */
@RestController
@RequestMapping("ops/v1/brands")
public class OpsBrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping("")
    public ResponseEntity<ResponseDTO> create(@RequestBody CreateBrandDTO createBrandDTO,
                                              @RequestAttribute(name = CRequestAttribute.REQUEST_ID) String requestId) {

        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setCode(createBrandDTO.getCode());
        brandDTO.setName(createBrandDTO.getName());
        ResponseDTO result = brandService.create(brandDTO, requestId);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable Long id,
                                              @RequestBody CreateBrandDTO createBrandDTO,
                                              @RequestAttribute(name = CRequestAttribute.REQUEST_ID) String requestId) {

        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setId(id);
        brandDTO.setCode(createBrandDTO.getCode());
        brandDTO.setName(createBrandDTO.getName());
        ResponseDTO result = brandService.update(brandDTO, requestId);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("")
    public ResponseEntity<ResponseDTO> getList(
            @RequestAttribute(name = CRequestAttribute.REQUEST_ID) String requestId,
            @RequestParam(name = CPagging.PAGE_NAME, defaultValue = CPagging.STR_DEFAULT_PAGE) int page,
            @RequestParam(name = CPagging.LIMIT_NAME, defaultValue = CPagging.STR_DEFAULT_LIMIT) int limit) {

        ResponseDTO result = brandService.getList(requestId, page, limit);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDTO> detail(@PathVariable Long id,
                                              @RequestAttribute(name = CRequestAttribute.REQUEST_ID) String requestId) {

        ResponseDTO result = brandService.detail(id, requestId);
        return ResponseEntity.ok().body(result);
    }

}
