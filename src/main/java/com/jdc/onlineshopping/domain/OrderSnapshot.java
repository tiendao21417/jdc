package com.jdc.onlineshopping.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jdc.onlineshopping.constant.EPaymentType;
import com.jdc.onlineshopping.constant.EStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author tiendao on 17/07/2021
 */
@Entity
@Table(name = "m_order_snapshot")
@Getter
@Setter
public class OrderSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "brand_code")
    private String brandCode;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "url")
    private String url;

    @Column(name = "unit_price")
    private double unitPrice;

    @Column(name = "amount")
    private int amount;

    @Column(name = "category_code")
    private String categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "colour")
    private String colour;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

}
