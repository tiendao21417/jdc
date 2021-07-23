package com.jdc.onlineshopping.app.api.web.rest;

import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tiendao on 16/07/2021
 */

@RestController
@RequestMapping("api/v1/ping")
public class PingController {

    @GetMapping("")
    public ResponseEntity<ResponseDTO> ping() {
        return null;
    }

}
