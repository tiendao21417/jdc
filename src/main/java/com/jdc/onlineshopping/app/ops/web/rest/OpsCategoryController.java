package com.jdc.onlineshopping.app.ops.web.rest;

import com.jdc.onlineshopping.constant.CPagging;
import com.jdc.onlineshopping.constant.CRequestAttribute;
import com.jdc.onlineshopping.app.ops.web.rest.dto.CreateCategoryDTO;
import com.jdc.onlineshopping.service.CategoryService;
import com.jdc.onlineshopping.web.rest.dto.CategoryDTO;
import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author tiendao on 22/07/2021
 */

@RestController
@RequestMapping("ops/v1/categories")
public class OpsCategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<ResponseDTO> create(@RequestBody CreateCategoryDTO dto,
                                              @RequestAttribute(name = CRequestAttribute.REQUEST_ID) String requestId) {

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCode(dto.getCode());
        categoryDTO.setName(dto.getName());
        ResponseDTO result = categoryService.create(categoryDTO, requestId);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable Long id,
                                              @RequestBody CreateCategoryDTO dto,
                                              @RequestAttribute(name = CRequestAttribute.REQUEST_ID) String requestId) {

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(id);
        categoryDTO.setCode(dto.getCode());
        categoryDTO.setName(dto.getName());
        ResponseDTO result = categoryService.update(categoryDTO, requestId);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("")
    public ResponseEntity<ResponseDTO> getList(@RequestAttribute(name = CRequestAttribute.REQUEST_ID) String requestId,
                                               @RequestParam(name = CPagging.PAGE_NAME, defaultValue = CPagging.STR_DEFAULT_PAGE) int page,
                                               @RequestParam(name = CPagging.LIMIT_NAME, defaultValue = CPagging.STR_DEFAULT_LIMIT) int limit) {

        ResponseDTO result = categoryService.getList(requestId, page, limit);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDTO> detail(@PathVariable Long id,
                                              @RequestAttribute(name = CRequestAttribute.REQUEST_ID) String requestId) {

        ResponseDTO result = categoryService.detail(id, requestId);
        return ResponseEntity.ok().body(result);
    }

}
