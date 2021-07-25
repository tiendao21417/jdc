package com.jdc.onlineshopping.service;

import com.jdc.onlineshopping.app.api.web.rest.dto.LoginDTO;
import com.jdc.onlineshopping.app.api.web.rest.dto.LoginResponseDTO;
import com.jdc.onlineshopping.constant.CErrors;
import com.jdc.onlineshopping.constant.UserType;
import com.jdc.onlineshopping.domain.User;
import com.jdc.onlineshopping.mapper.UserMapper;
import com.jdc.onlineshopping.utils.ResponseUtils;
import com.jdc.onlineshopping.utils.Throws;
import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;
import com.jdc.onlineshopping.web.rest.dto.UserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.MessageSource;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public interface BaseLoginService {

    ResponseDTO login(LoginDTO loginDTO);

    ResponseDTO register(UserDTO userDTO);

    default LoginResponseDTO handleLoginInternal(LoginDTO loginDTO, long getJwtTokenValidity,
                                                 String secret, UserService userService, MessageSource messageSource) {

        User user = userService.loadUserByEmail(loginDTO.getEmail());
        if (user == null) {
            Throws.jdca(CErrors.AUTHENTICATE_FAILED_USERNAME_NOT_FOUND,
                    messageSource.getMessage(CErrors.AUTHENTICATE_FAILED_USERNAME_NOT_FOUND,
                            new Object[]{"body"}, null));
        }
        if (!user.getPassword().equals(loginDTO.getPassword())) {
            Throws.jdca(CErrors.AUTHENTICATE_FAILED_WRONG_PASSWORD,
                    messageSource.getMessage(CErrors.AUTHENTICATE_FAILED_WRONG_PASSWORD,
                            new Object[]{"body"}, null));
        }

        String subject = user.getEmail();
        Map<String, Object> claims = new HashMap<>();
        String jwt = Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + getJwtTokenValidity * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setToken(jwt);
        return loginResponseDTO;
    }

    default ResponseDTO registerInternal(UserDTO userDTO, UserService userService, MessageSource messageSource, UserMapper userMapper, UserType customer) {

        User user = userService.loadUserByEmail(userDTO.getEmail());
        if (user != null) {
            Throws.jdca(CErrors.CREATE_NEW_USER_FAILED_BY_ACCOUNT_EXIST,
                    messageSource.getMessage(CErrors.CREATE_NEW_USER_FAILED_BY_ACCOUNT_EXIST,
                            new Object[]{"body"}, null));
        }
        user = userService.save(userDTO);
        return ResponseUtils.responseOK(userMapper.toDto(user));
    }
}
