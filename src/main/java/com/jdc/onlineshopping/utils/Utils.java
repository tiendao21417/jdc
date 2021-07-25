package com.jdc.onlineshopping.utils;

import java.util.UUID;

/**
 * @author tiendao on 18/07/2021
 */
public class Utils {

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static boolean validateLatitude(double lat) {
        if (lat == 0 || lat < -90 || lat > 90)
            return false;
        return true;
    }

    public static boolean validateLongitude(double lng) {
        if (lng == 0 || lng < -180 || lng > 180)
            return false;
        return true;
    }
}
