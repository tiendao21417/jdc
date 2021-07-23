package com.jdc.onlineshopping.app.ops.web.rest;

import com.jdc.onlineshopping.constant.CPagging;
import com.jdc.onlineshopping.constant.CRequestAttribute;
import com.jdc.onlineshopping.domain.User;
import com.jdc.onlineshopping.app.ops.web.rest.dto.CreateProductDTO;
import com.jdc.onlineshopping.service.ProductService;
import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author tiendao on 17/07/2021
 */
@RestController
@RequestMapping("ops/v1/products")
public class OpsProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<ResponseDTO> create(@RequestBody CreateProductDTO dto,
                                              @RequestAttribute(name = CRequestAttribute.REQUEST_ID) String requestId) {

        ResponseDTO result = productService.create(dto, requestId);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable Long id,
                                              @RequestBody CreateProductDTO dto,
                                              @RequestAttribute(name = CRequestAttribute.USER) User user,
                                              @RequestAttribute(name = CRequestAttribute.REQUEST_ID) String requestId) {


        ResponseDTO result = productService.update(id, dto, requestId);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("")
    public ResponseEntity<ResponseDTO> getList(@RequestAttribute(name = CRequestAttribute.REQUEST_ID) String requestId,
                                               @RequestParam(name = CPagging.PAGE_NAME, defaultValue = CPagging.STR_DEFAULT_PAGE) int page,
                                               @RequestParam(name = CPagging.LIMIT_NAME, defaultValue = CPagging.STR_DEFAULT_LIMIT) int limit) {

        ResponseDTO result = productService.getList(requestId, page, limit);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDTO> detail(@PathVariable Long id,
                                              @RequestAttribute(name = CRequestAttribute.REQUEST_ID) String requestId) {

        ResponseDTO result = productService.detail(id, requestId);
        return ResponseEntity.ok().body(result);
    }

}