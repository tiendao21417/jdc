package com.jdc.onlineshopping.aop.logging.dto;

import lombok.Data;

/**
 * @author tiendao on 22/07/2021
 */
@Data
public class ErrorDTO {

    private String requestId;

    private String method;

    private String uri;

    private String query;

    private String body;

    private String code;

    private long latency;

    private String message;

    private String throwName;

    private Object stackTraces;
}
