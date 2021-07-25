package com.jdc.onlineshopping.app.api.service;

import com.jdc.onlineshopping.app.api.web.rest.dto.LoginDTO;
import com.jdc.onlineshopping.app.api.web.rest.dto.LoginResponseDTO;
import com.jdc.onlineshopping.constant.UserType;
import com.jdc.onlineshopping.mapper.UserMapper;
import com.jdc.onlineshopping.service.UserService;
import com.jdc.onlineshopping.utils.ResponseUtils;
import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;
import com.jdc.onlineshopping.web.rest.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 * @author tiendao on 22/07/2021
 */
@Service
public class LoginServiceImpl implements LoginService {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${api.jwt.validity:18000}")
    private long getJwtTokenValidity;

    @Value("${api.jwt.secret}")
    private String secret;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseDTO login(LoginDTO loginDTO) {

        LoginResponseDTO loginResponseDTO = handleLoginInternal(loginDTO, getJwtTokenValidity, secret, userService, messageSource);
        return ResponseUtils.responseOK(loginResponseDTO);
    }

    @Override
    public ResponseDTO register(UserDTO userDTO) {

       return registerInternal(userDTO, userService, messageSource, userMapper, UserType.CUSTOMER);
    }

}
