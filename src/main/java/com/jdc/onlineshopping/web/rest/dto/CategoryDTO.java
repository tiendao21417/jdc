package com.jdc.onlineshopping.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author tiendao on 22/07/2021
 */
// @Getter
// @Setter
public class CategoryDTO /*extends AlgoliaDTO */{

    @JsonProperty("objectID")
    private String objectID;

    @JsonProperty("id")
    private long id;

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;


    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
