package com.jdc.onlineshopping.app.ops.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author tiendao on 18/07/2021
 */
@Data
public class CreateBrandDTO {

    @NotBlank(message = "code not be empty")
    @JsonProperty("code")
    private String code;

    @NotBlank(message = "name not be empty")
    @JsonProperty("name")
    private String name;
}
