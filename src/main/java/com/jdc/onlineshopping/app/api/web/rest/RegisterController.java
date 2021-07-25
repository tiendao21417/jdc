package com.jdc.onlineshopping.app.api.web.rest;

import com.jdc.onlineshopping.app.api.service.LoginService;
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
@RequestMapping("api/v1/register")
public class RegisterController {

    @Autowired
    private LoginService loginService;

    @PostMapping("")
    public ResponseEntity<ResponseDTO> register(@Valid @RequestBody UserDTO userDTO) {

        ResponseDTO responseDTO = loginService.register(userDTO);
        return ResponseEntity.ok().body(responseDTO);
    }
}
