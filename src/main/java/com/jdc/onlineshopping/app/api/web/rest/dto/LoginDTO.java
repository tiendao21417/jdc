package com.jdc.onlineshopping.app.api.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author tiendao on 22/07/2021
 */
@Data
public class LoginDTO {

    @NotBlank(message = "email is not valid")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "password is not valid")
    @JsonProperty("password")
    private String password;
}
