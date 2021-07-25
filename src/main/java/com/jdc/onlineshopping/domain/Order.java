package com.jdc.onlineshopping.domain;

import com.jdc.onlineshopping.constant.EPaymentType;
import com.jdc.onlineshopping.constant.EStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author tiendao on 24/07/2021
 */
@Entity
@Table(name = "m_order")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "customer_address")
    private String customerAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<OrderSnapshot> orderSnapshots = new HashSet<>();

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private EPaymentType paymentMethod;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EStatus status;
}
