package com.jdc.onlineshopping.web.rest.dto;

/**
 * @author tiendao on 17/07/2021
 */
public class ResponseDTO {

    private MetadataDTO meta;
    private Object data;

    public ResponseDTO() {
    }

    public ResponseDTO(MetadataDTO meta, Object data) {
        this.meta = meta;
        this.data = data;
    }

    public MetadataDTO getMeta() {
        return meta;
    }

    public void setMeta(MetadataDTO meta) {
        this.meta = meta;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
