package com.jdc.onlineshopping.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tiendao on 22/07/2021
 */
public class HTTPUtils {

    public static String getAttributeAsString(HttpServletRequest request, String key) {
        return (String) request.getAttribute(key);
    }

    public static Object getAttribute(HttpServletRequest request, String key) {
        return request.getAttribute(key);
    }

    public static int getAttributeAsInt(HttpServletRequest request, String key) {
        Object data = request.getAttribute(key);
        if (data == null) {
            return 0;
        }
        if (data instanceof Integer) {
            return (Integer) data;
        }
        return 0;
    }

    public static long getAttributeAsLong(HttpServletRequest request, String key) {
        Object data = request.getAttribute(key);
        if (data == null) {
            return 0;
        }
        if (data instanceof Long) {
            return (Long) data;
        }
        return 0;
    }

    public static String getBody(String body) {
        if (body != null) {
            return body;
        }
        return "";
    }

    public static void setAttribute(HttpServletRequest request, String key, String value) {
        request.setAttribute(key, value);
    }
}
