package com.jdc.onlineshopping.utils;

import com.jdc.onlineshopping.aop.exception.JDCAuthenticationException;
import com.jdc.onlineshopping.aop.exception.JDCAuthorizationException;
import com.jdc.onlineshopping.aop.exception.JDCRequestException;

/**
 * @author tiendao on 22/07/2021
 */
public class Throws {

    public static void jdca(String code, String message, Object... parameters) {
        throw new JDCAuthenticationException(code, String.format(message, parameters));
    }

    public static void jdcar(String code, String message, Object... parameters) {
        throw new JDCAuthorizationException(code, String.format(message, parameters));
    }

    public static void of(String code, String message, Object... parameters) {
        throw new JDCRequestException(code, String.format(message, parameters));
    }
}
