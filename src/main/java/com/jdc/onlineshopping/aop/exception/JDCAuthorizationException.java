package com.jdc.onlineshopping.aop.exception;

/**
 * @author tiendao on 22/07/2021
 */
public class JDCAuthorizationException extends  JDCRuntimeException {

    public JDCAuthorizationException(String code, String format) {
        super(code, format);
    }
}
