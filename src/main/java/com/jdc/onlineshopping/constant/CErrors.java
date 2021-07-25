package com.jdc.onlineshopping.constant;

/**
 * @author tiendao on 22/07/2021
 */
public class CErrors {

    public static final String BAD_REQUEST_BY_WRONG_PARAMETER = "4000001";

    public static final String AUTHENTICATE_FAILED_USERNAME_NOT_FOUND = "4010001";
    public static final String AUTHENTICATE_FAILED_WRONG_PASSWORD = "4010002";

    public static final String CREATE_NEW_USER_FAILED_BY_ACCOUNT_EXIST = "4001001";

    public static final String CREATE_ORDER_FAILED_BY_EMPTY_CART = "4002001";
    public static final String CREATE_ORDER_FAILED_BY_PRODUCT_NOT_FOUND = "4002002";
    public static final String CREATE_ORDER_FAILED_BY_OUT_STOCK = "4002003";

    public static final String TOKEN_IS_NOT_VALID = "4003001";
    public static final String TOKEN_IS_EXPIRED = "4003002";

    public static final String ORDER_IS_NOT_FOUND = "4004001";

}
