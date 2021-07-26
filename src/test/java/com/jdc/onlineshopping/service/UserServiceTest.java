package com.jdc.onlineshopping.service;

import com.jdc.onlineshopping.constant.UserType;
import com.jdc.onlineshopping.domain.User;
import com.jdc.onlineshopping.mapper.UserMapper;
import com.jdc.onlineshopping.mapper.UserMapperImpl;
import com.jdc.onlineshopping.repository.UserRepository;
import com.jdc.onlineshopping.web.rest.dto.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@SpringBootTest(classes = {UserMapperImpl.class, UserServiceImpl.class})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void saveUser() throws Exception {

        User user = new User();
        user.setId(1);
        user.setName("Tien Dao");
        user.setEmail("tien.dao21417@gmail.com");
        user.setFullAddress("1D Phuoc Long B, district 9, HCM");
        user.setPhoneNumber("0907958077");
        user.setType(UserType.CUSTOMER);

        UserDTO userDTO = new UserDTO();
        userDTO.setName("Tien Dao");
        userDTO.setEmail("tien.dao21417@gmail.com");
        userDTO.setFullAddress("1D Phuoc Long B, district 9, HCM");
        userDTO.setPhoneNumber("0907958077");

        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);

        user = userService.save(userDTO);
        assertThat(user.getEmail()).isEqualTo(userDTO.getEmail());
        assertThat(user.getName()).isEqualTo(userDTO.getName());
        assertThat(user.getFullAddress()).isEqualTo(userDTO.getFullAddress());
        assertThat(user.getPhoneNumber()).isEqualTo(userDTO.getPhoneNumber());
    }

}
