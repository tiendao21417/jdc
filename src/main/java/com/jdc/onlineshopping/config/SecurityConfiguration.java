package com.jdc.onlineshopping.config;

import com.jdc.onlineshopping.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.List;

/**
 * @author tiendao on 18/07/2021
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
// @Import(SecurityProblemSupport.class)
public class SecurityConfiguration {

    @Configuration
    public class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        private final HandlerExceptionResolver resolver;
        private final MessageSource messageSource;
        private final UserService userService;

        @Value("${api.jwt.secret}")
        private String apiSecret;

        @Value("${ops.jwt.secret}")
        private String opsSecret;

        @Value("${url.permit.all}")
        private List<String> ignoreUrls;

        public ApiWebSecurityConfigurationAdapter(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver,
                                                  MessageSource messageSource,
                                                  UserService userService) {
            this.resolver = resolver;
            this.messageSource = messageSource;
            this.userService = userService;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        public void configure(WebSecurity web) {
            web.ignoring()
                    .antMatchers(HttpMethod.OPTIONS, "/**")
                    .antMatchers("/swagger-ui/index.html")
                    .antMatchers("/test/**");
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                    .csrf()
                    .disable()
                    .headers()
                    .contentSecurityPolicy("default-src 'self'; frame-src 'self' data:; script-src 'self' 'unsafe-inline' 'unsafe-eval' https://storage.googleapis.com; style-src 'self' 'unsafe-inline'; img-src 'self' data:; font-src 'self' data:")
                    .and()
                    .referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
                    .and()
                    .featurePolicy("geolocation 'none'; midi 'none'; sync-xhr 'none'; microphone 'none'; camera 'none'; magnetometer 'none'; gyroscope 'none'; speaker 'none'; fullscreen 'self'; payment 'none'")
                    .and()
                    .frameOptions()
                    .deny()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/api/v1/login", "/api/v1/register", "/ops/v1/login", "/ops/v1/register").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .apply(securityConfigurerAdapter());
            // @formatter:on
        }

        private ApiSecurityConfigurerAdapter securityConfigurerAdapter() {
            return new ApiSecurityConfigurerAdapter(resolver,
                    messageSource, userService, apiSecret, opsSecret, ignoreUrls);
        }
    }
}
