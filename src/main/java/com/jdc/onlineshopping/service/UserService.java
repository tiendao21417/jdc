package com.jdc.onlineshopping.service;

import com.jdc.onlineshopping.domain.User;
import com.jdc.onlineshopping.web.rest.dto.UserDTO;

public interface UserService {

    User loadUserByEmail(String email);

    User save(UserDTO userDTO);
}
