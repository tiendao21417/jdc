package com.jdc.onlineshopping.app.api.web.rest;

import com.jdc.onlineshopping.app.api.service.LoginService;
import com.jdc.onlineshopping.app.api.web.rest.dto.LoginDTO;
import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
// import javax.validation.Valid;

/**
 * @author tiendao on 22/07/2021
 */
@RestController
@RequestMapping("api/v1/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("")
    public ResponseEntity<ResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {

        ResponseDTO responseDTO = loginService.login(loginDTO);
        return ResponseEntity.ok().body(responseDTO);
    }
}
