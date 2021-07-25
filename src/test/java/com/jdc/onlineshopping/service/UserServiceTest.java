package com.jdc.onlineshopping.service;

import com.jdc.onlineshopping.aop.logging.LoggerProvider;
import com.jdc.onlineshopping.domain.User;
import com.jdc.onlineshopping.repository.UserRepository;
import com.jdc.onlineshopping.web.rest.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author tiendao on 25/07/2021
 */
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void saveUser() {

        // Mock.
        UserDTO userDTO = new UserDTO();
        User user = userService.save(userDTO);

    }

}
