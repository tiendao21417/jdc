package com.jdc.onlineshopping.aop.exception;

/**
 * @author tiendao on 22/07/2021
 */
public class JDCRequestException extends JDCRuntimeException {

    public JDCRequestException(String code, String format) {
        super(code, format);
    }
}
