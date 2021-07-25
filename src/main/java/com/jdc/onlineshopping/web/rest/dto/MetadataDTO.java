package com.jdc.onlineshopping.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tiendao on 17/07/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetadataDTO {

    private String code;

    private String message;

    private String cursor;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer page;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer limit;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long total;

    public MetadataDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public MetadataDTO(String code, String message, String cursor) {
        this.code = code;
        this.message = message;
        this.cursor = cursor;
    }
}
