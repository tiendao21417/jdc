package com.jdc.onlineshopping.ops.web.rest;

import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tiendao on 17/07/2021
 */
@RestController("opsProductController")
@RequestMapping("ops/v1/products")
public class ProductController {

    @GetMapping
    public ResponseEntity<ResponseDTO> getList() {

        return null;
    };

    @GetMapping("{productId}")
    public ResponseEntity<ResponseDTO> detail() {

        return null;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create() {

        return null;
    }

    @PostMapping("{productId}")
    public ResponseEntity<ResponseDTO> update() {

        return null;
    }
}
