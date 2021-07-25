package com.jdc.onlineshopping.mapper;

import com.jdc.onlineshopping.domain.User;
import com.jdc.onlineshopping.web.rest.dto.UserDTO;
import org.mapstruct.Mapper;

/**
 * @author tiendao on 22/07/2021
 */
@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User>{
}
