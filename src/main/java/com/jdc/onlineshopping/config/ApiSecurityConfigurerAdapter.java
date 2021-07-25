package com.jdc.onlineshopping.config;

import com.jdc.onlineshopping.config.security.APIFilter;
import com.jdc.onlineshopping.service.UserService;
import org.springframework.context.MessageSource;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.List;

/**
 * @author tiendao on 18/07/2021
 */
public class ApiSecurityConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final HandlerExceptionResolver resolver;
    private final MessageSource messageSource;
    private final String apiSecret;
    private final UserService userService;
    private final String opsSecret;
    private final List<String> ignoreUrls;

    public ApiSecurityConfigurerAdapter(HandlerExceptionResolver resolver, MessageSource messageSource,
                                        UserService userService, String apiSecret, String opsSecret, List<String> ignoreUrls) {

        this.resolver = resolver;
        this.messageSource = messageSource;
        this.userService = userService;
        this.apiSecret = apiSecret;
        this.opsSecret = opsSecret;
        this.ignoreUrls =ignoreUrls;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        APIFilter customFilter = new APIFilter(resolver, messageSource, userService, apiSecret, opsSecret, ignoreUrls);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}