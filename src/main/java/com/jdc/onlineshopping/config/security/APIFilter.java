package com.jdc.onlineshopping.config.security;

import com.jdc.onlineshopping.aop.logging.LoggerProvider;
import com.jdc.onlineshopping.constant.CErrors;
import com.jdc.onlineshopping.constant.CRequestAttribute;
import com.jdc.onlineshopping.domain.User;
import com.jdc.onlineshopping.service.UserService;
import com.jdc.onlineshopping.utils.Throws;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

/**
 * @author tiendao on 18/07/2021
 */
public class APIFilter extends JDCFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String API_START_WITH = "/api/";
    public static final String OPS_START_WITH = "/ops/";
    private final List<String> ignoreUrls;

    public APIFilter(HandlerExceptionResolver resolver, MessageSource messageSource,
                     UserService userService, String apiSecret, String opsSecret, List<String> ignoreUrls) {

        super(resolver);
        this.messageSource = messageSource;
        this.userService = userService;
        this.apiSecret = apiSecret;
        this.opsSecret = opsSecret;
        this.ignoreUrls = ignoreUrls;
    }

    private final MessageSource messageSource;

    private final String apiSecret;

    private final String opsSecret;

    private final UserService userService;

    @Override
    protected boolean doFilterInternal(ServletRequest request, ServletResponse response) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();

        if (this.ignoreUrls != null && !this.ignoreUrls.isEmpty()) {
            for (String ignore : this.ignoreUrls) {
              if (requestURI.equals(ignore)) {
                  return false;
              }
          }
        }
        if (requestURI.startsWith(API_START_WITH)) {
            return handleApiFilter(httpServletRequest, response);
        }

        if (requestURI.startsWith(OPS_START_WITH)) {
            return handleOpsFilter(httpServletRequest, response);
        }

        Throws.jdcar(CErrors.TOKEN_IS_NOT_VALID, messageSource.getMessage(CErrors.TOKEN_IS_NOT_VALID,
                new Object[]{"body"}, null));
        return false;
    }

    private boolean handleOpsFilter(HttpServletRequest httpServletRequest, ServletResponse response) {

        try {
            handleFilterInternal(httpServletRequest, response, opsSecret);
        } catch (JwtException e) {
            Throws.jdcar(CErrors.TOKEN_IS_NOT_VALID, messageSource.getMessage(CErrors.TOKEN_IS_NOT_VALID,
                    new Object[]{"body"}, null));
        }

        return false;
    }

    private void handleFilterInternal(HttpServletRequest httpServletRequest, ServletResponse response, String secret) {
        String jwt = getJwt(httpServletRequest);
        if (jwt == null || "".equals(jwt)) {
            Throws.jdca(CErrors.TOKEN_IS_NOT_VALID, messageSource.getMessage(CErrors.TOKEN_IS_NOT_VALID,
                    new Object[]{"body"}, null));
        }
        Claims claims = getAllClaimsFromToken(jwt, secret);
        if (isTokenExpired(claims)) {
            Throws.jdca(CErrors.TOKEN_IS_EXPIRED, messageSource.getMessage(CErrors.TOKEN_IS_EXPIRED,
                    new Object[]{"body"}, null));
        }
        String email = claims.getSubject();
        User user = userService.loadUserByEmail(email);
        if (user == null) {
            Throws.jdcar(CErrors.AUTHENTICATE_FAILED_USERNAME_NOT_FOUND, messageSource.getMessage(CErrors.AUTHENTICATE_FAILED_USERNAME_NOT_FOUND,
                    new Object[]{"body"}, null));
        }
        httpServletRequest.setAttribute(CRequestAttribute.USER, user);
        Authentication authentication =  new UsernamePasswordAuthenticationToken(email, jwt, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    private boolean handleApiFilter(HttpServletRequest httpServletRequest, ServletResponse response) {

        try {
            handleFilterInternal(httpServletRequest, response, apiSecret);
        } catch (JwtException e) {
            Throws.jdcar(CErrors.TOKEN_IS_NOT_VALID, messageSource.getMessage(CErrors.TOKEN_IS_NOT_VALID,
                    new Object[]{"body"}, null));
        }
        return false;
    }

    private String getJwt(HttpServletRequest httpServletRequest) {

        String bearerToken = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    //for retrieving any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token, String secret) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

}
