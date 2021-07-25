package com.jdc.onlineshopping.aop.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tiendao on 22/07/2021
 */
public class LoggerProvider {

    public static Logger APP = LoggerFactory.getLogger("JDC-API-LOGGER");
    public static Logger PERFORMANCE = LoggerFactory.getLogger("JDC-PERFORMANCE-LOGGER");
    public static Logger MONITOR = LoggerFactory.getLogger("JDC-MONITOR-LOGGER");
}
