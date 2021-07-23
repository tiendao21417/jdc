package com.jdc.onlineshopping.app.ops.web.rest;

import com.jdc.onlineshopping.app.api.web.rest.dto.LoginDTO;
import com.jdc.onlineshopping.app.ops.service.OpsLoginService;
import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;
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
@RequestMapping("ops/v1/login")
public class OpsLoginController {

    @Autowired
    private OpsLoginService loginService;

    @PostMapping("")
    public ResponseEntity<ResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {

        ResponseDTO responseDTO = loginService.login(loginDTO);
        return ResponseEntity.ok().body(responseDTO);
    }
}
