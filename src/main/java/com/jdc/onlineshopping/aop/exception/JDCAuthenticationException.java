package com.jdc.onlineshopping.aop.exception;

/**
 * @author tiendao on 22/07/2021
 */
public class JDCAuthenticationException extends JDCRuntimeException {

    public JDCAuthenticationException(String code, String format) {
        super(code, format);
    }
}
