package com.jdc.onlineshopping.common.exception;

import lombok.*;

/**
 * @author tiendao on 17/07/2021
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JDCRuntimeException  extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;
}