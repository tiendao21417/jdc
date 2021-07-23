package com.jdc.onlineshopping.aop.exception.handler;

import com.jdc.onlineshopping.aop.exception.JDCAuthenticationException;
import com.jdc.onlineshopping.aop.logging.LoggerProvider;
import com.jdc.onlineshopping.aop.logging.dto.ErrorDTO;
import com.jdc.onlineshopping.constant.CRequestAttribute;
import com.jdc.onlineshopping.aop.exception.JDCAuthorizationException;
import com.jdc.onlineshopping.aop.exception.JDCRuntimeException;
import com.jdc.onlineshopping.utils.HTTPUtils;
import com.jdc.onlineshopping.utils.JsonSupport;
import com.jdc.onlineshopping.utils.ResponseUtils;
import com.jdc.onlineshopping.web.rest.dto.MetadataDTO;
import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.security.SignatureException;

/**
 * @author tiendao on 22/07/2021
 */
@EnableWebMvc
@ControllerAdvice
public class ExceptionHandlerAdvice {



    @ExceptionHandler(SignatureException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ResponseDTO> handle(HttpServletRequest request, SignatureException e) {

        buildInfoLog(request, String.valueOf(HttpStatus.UNAUTHORIZED.value()), e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
                body(createErrorBody(request, String.valueOf(HttpStatus.UNAUTHORIZED.value()), e.getMessage()));
    }

    @ExceptionHandler(JDCAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ResponseDTO> handle(HttpServletRequest request, JDCAuthenticationException e) {

        buildInfoLog(request, e.getCode(), e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
                body(createErrorBody(request, e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(JDCAuthorizationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ResponseDTO> handle(HttpServletRequest request, JDCAuthorizationException e) {

        buildInfoLog(request, e.getCode(), e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
                body(createErrorBody(request, e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ResponseDTO> handle(HttpServletRequest request, ExpiredJwtException e) {

        LoggerProvider.APP.info("ExpiredJwtException");
        buildInfoLog(request, String.valueOf(HttpStatus.UNAUTHORIZED.value()), e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
                body(createErrorBody(request, String.valueOf(HttpStatus.UNAUTHORIZED.value()), e.getMessage()));
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseDTO> handle(HttpServletRequest request, Throwable e) {

        buildErrorLog(request, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), "INTERNAL SERVER ERROR", e.getStackTrace(), e.getClass().getName());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(createErrorBody(request, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), "INTERNAL SERVER ERROR"));
    }

    private ResponseEntity<ResponseDTO> badRequest(HttpServletRequest request, RuntimeException e) {

        String code = String.valueOf(HttpStatus.BAD_REQUEST.value());
        buildInfoLog(request, code, e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(createErrorBody(request, code, e.getMessage()));
    }


    private void buildInfoLog(HttpServletRequest request, String code, String message, StackTraceElement[] stackTrace) {

        LoggerProvider.APP.info(JsonSupport.toJson(buildLog(request, code, message, stackTrace, null)));
    }

    private void buildErrorLog(HttpServletRequest request, String code, String message, StackTraceElement[] stackTrace, String throwName) {

        LoggerProvider.APP.error(JsonSupport.toJson(buildLog(request, code, message, stackTrace, throwName)));
    }

    private ErrorDTO buildLog(HttpServletRequest request, String code, String message,
                              StackTraceElement[] stackTrace, String throwName) {
        String requestId = HTTPUtils.getAttributeAsString(request, CRequestAttribute.REQUEST_ID);
        ErrorDTO dto = new ErrorDTO();
        dto.setRequestId(requestId);
        dto.setMethod(request.getMethod());
        dto.setUri(request.getRequestURI());
        dto.setQuery(request.getQueryString());
        // dto.setBody(getBody(request));
        dto.setCode(code);
        dto.setMessage(message);
        dto.setThrowName(throwName);
        dto.setStackTraces(stackTrace);
        return dto;
    }

    private void buildErrorLog(HttpServletRequest request, JDCRuntimeException e) {
        buildErrorLog(request, e.getCode(), e.getMessage(), e.getStackTrace(), e.getClass().getName());
    }

    private void buildInfoLog(HttpServletRequest request, JDCRuntimeException e) {
        buildInfoLog(request, e.getCode(), e.getMessage(), e.getStackTrace());
    }

    private ResponseDTO createErrorBody(HttpServletRequest request, String error, String message) {
        return createErrorBody(request, ResponseUtils.buildError(error, message));
    }

    private ResponseDTO createErrorBody(HttpServletRequest request, JDCRuntimeException e) {
        return createErrorBody(request, ResponseUtils.buildError(e));
    }

    private ResponseDTO createErrorBody(HttpServletRequest request, MetadataDTO metaData) {
        ResponseDTO body = ResponseUtils.buildError(metaData);
        return createErrorBody(request, body);
    }

    private ResponseDTO createErrorBody(HttpServletRequest request, ResponseDTO body) {

        if (body.getMeta() != null) {
            String message = body.getMeta().getMessage();
            if (message == null) {
                message = "";
            }
            String requestId = HTTPUtils.getAttributeAsString(request, CRequestAttribute.REQUEST_ID);
            if (requestId != null) {
                String fiveLastChar;
                if (requestId.length() > 5) {
                    fiveLastChar = requestId.substring(requestId.length() - 5);
                } else {
                    fiveLastChar = requestId;
                }

                message = message + String.format("(%s)", fiveLastChar);
            }
            body.getMeta().setMessage(message);
        }
        return body;
    }
}
