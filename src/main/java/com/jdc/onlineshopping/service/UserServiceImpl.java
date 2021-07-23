package com.jdc.onlineshopping.service;

import com.jdc.onlineshopping.domain.User;
import com.jdc.onlineshopping.mapper.UserMapper;
import com.jdc.onlineshopping.repository.UserRepository;
import com.jdc.onlineshopping.web.rest.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author tiendao on 22/07/2021
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User loadUserByEmail(String email) {

        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return null;
        }
        return userOptional.get();
    }

    @Override
    public User save(UserDTO userDTO) {

        User user = userMapper.toEntity(userDTO);
        return userRepository.save(user);
    }
}
