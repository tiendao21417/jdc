package com.jdc.onlineshopping.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

/**
 * @author tiendao on 22/07/2021
 */
@Data
public class UserDTO {

    @JsonProperty("id")
    private long id;

    @NotBlank(message = "email is not valid")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "password is not valid")
    @JsonProperty("password")
    private String password;

    @JsonProperty("name")
    private String name;

    @JsonProperty("full_address")
    private String fullAddress;

    @JsonProperty("phone_number")
    private String phoneNumber;
}
