package com.jdc.onlineshopping.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tiendao on 23/07/2021
 */
@Getter
@Setter
public class AlgoliaDTO {

    @JsonProperty("objectID")
    private String objectID;
}
