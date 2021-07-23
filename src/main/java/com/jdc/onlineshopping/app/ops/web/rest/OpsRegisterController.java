package com.jdc.onlineshopping.app.ops.web.rest;

import com.jdc.onlineshopping.app.ops.service.OpsLoginService;
import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;
import com.jdc.onlineshopping.web.rest.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author tiendao on 22/07/2021
 */
@RestController
@RequestMapping("ops/v1/register")
public class OpsRegisterController {

    @Autowired
    private OpsLoginService loginService;

    @PostMapping("")
    public ResponseEntity<ResponseDTO> register(@Valid @RequestBody UserDTO userDTO) {

        ResponseDTO responseDTO = loginService.register(userDTO);
        return ResponseEntity.ok().body(responseDTO);
    }
}
