package com.jdc.onlineshopping.app.api.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jdc.onlineshopping.constant.EPaymentType;
import com.jdc.onlineshopping.constant.EStatus;
import com.jdc.onlineshopping.domain.OrderSnapshot;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author tiendao on 24/07/2021
 */
@Getter
@Setter
public class OrderDTO {

    @JsonProperty("id")
    private long id;

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("customer_phone")
    private String customerPhone;

    @JsonProperty("customer_address")
    private String customerAddress;

    @JsonProperty("items")
    private Set<OrderItem> orderSnapshots = new HashSet<>();

    @JsonProperty("total_price")
    private double totalPrice;

    @JsonProperty("payment_method")
    @Enumerated(EnumType.STRING)
    private EPaymentType paymentMethod;

    @JsonProperty("status")
    @Enumerated(EnumType.STRING)
    private EStatus status;
}
