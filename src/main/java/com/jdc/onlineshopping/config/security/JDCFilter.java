package com.jdc.onlineshopping.config.security;

import com.jdc.onlineshopping.constant.CRequestAttribute;
import com.jdc.onlineshopping.constant.CRequestHeader;
import com.jdc.onlineshopping.aop.exception.JDCRuntimeException;
import com.jdc.onlineshopping.utils.Utils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tiendao on 18/07/2021
 */
public abstract class JDCFilter extends GenericFilterBean {

    private final HandlerExceptionResolver resolver;

    public JDCFilter(HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    private void beforeByPass(ServletRequest request, ServletResponse response) {

        String requestId = null;
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            requestId = httpServletRequest.getHeader(CRequestHeader.HEADER_REQUEST_ID);
        }
        if (requestId == null || "".equals(requestId)) {
            requestId = Utils.generateUUID();
        }
        request.setAttribute(CRequestAttribute.REQUEST_ID, requestId);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        beforeByPass(request, response);
        boolean isIgnoreNextChain = false;
        try {
            isIgnoreNextChain = doFilterInternal(request, response);
        } catch (JDCRuntimeException ode) {
            resolver.resolveException((HttpServletRequest) request, (HttpServletResponse) response, null, ode);
            return;
        }
        if (!isIgnoreNextChain) {
            nextChain(chain, request, response);
        }
    }

    protected void nextChain(FilterChain chain, ServletRequest request, ServletResponse response) throws ServletException, IOException {
        chain.doFilter(request, response);
    }

    /**
     * @param request
     * @param response
     * @return is ignore next chain.\n True if do not run chain.doFilter(). \n
     * @throws IOException
     * @throws ServletException
     */
    protected abstract boolean doFilterInternal(ServletRequest request, ServletResponse response) throws IOException, ServletException;
}
