package com.jdc.onlineshopping.app.ops.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author tiendao on 22/07/2021
 */
@Data
public class CreateCategoryDTO {

    @NotBlank(message = "code not be empty")
    @JsonProperty("code")
    private String code;

    @NotBlank(message = "name not be empty")
    @JsonProperty("name")
    private String name;
}
