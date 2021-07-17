package com.jdc.onlineshopping.utils;

import com.jdc.onlineshopping.common.exception.JDCRuntimeException;
import com.jdc.onlineshopping.web.rest.dto.MetadataDTO;
import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

/**
 * @author tiendao on 17/07/2021
 */
public class ResponseUtils {

    public static final String CODE_OK = "200";
    public static final String MESSAGE_OK = "Success";

    public static final String CODE_BAD_REQUEST = "400";
    public static final String CODE_FORBIDDEN = "403";

    public static ResponseDTO buildResponse(String code, String message, Object data) {
        MetadataDTO meta = new MetadataDTO(code, message);
        return new ResponseDTO(meta, data);
    }

    public static ResponseDTO buildResponse(String code, String message, String cursor, Object data) {
        MetadataDTO meta = new MetadataDTO(code, message, cursor);
        return new ResponseDTO(meta, data);
    }

    public static ResponseDTO responseOK(Object data) {
        return buildResponse(CODE_OK, MESSAGE_OK, data);
    }

    public static ResponseDTO responseOK(String cursor, Object data) {
        return buildResponse(CODE_OK, MESSAGE_OK, cursor, data);
    }

    public static ResponseDTO responseBadRequest(String message) {
        return buildResponse(CODE_BAD_REQUEST, message, null);
    }

    public static ResponseDTO buildError(MetadataDTO metaData) {
        return new ResponseDTO(metaData, null);
    }

    public static ResponseDTO buildError(JDCRuntimeException e) {
        return new ResponseDTO(new MetadataDTO(e.getCode(), e.getMessage()), null);
    }

    public static ResponseDTO buildError(String code, String message) {
        return new ResponseDTO(new MetadataDTO(code, message), null);
    }

    public static ResponseEntity<ResponseDTO> response(ResponseDTO responseDTO) {
        return ResponseEntity.ok().body(responseDTO);
    }

    public static ResponseDTO buildResponse(String code, String message, Integer page, Integer limit, Long total, Object data) {
        MetadataDTO meta = new MetadataDTO();
        meta.setCode(code);
        meta.setMessage(message);
        meta.setPage(page);
        meta.setLimit(limit);
        meta.setTotal(total);
        return new ResponseDTO(meta, data);
    }
}
