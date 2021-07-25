package com.jdc.onlineshopping.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tiendao on 23/07/2021
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlgoliaDTO {

    @JsonProperty("objectID")
    private String objectID;
}
