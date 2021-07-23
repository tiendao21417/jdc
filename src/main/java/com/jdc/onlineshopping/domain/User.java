package com.jdc.onlineshopping.domain;

import com.jdc.onlineshopping.constant.UserType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author tiendao on 21/07/2021
 */
@Entity
@Table(name = "m_product")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "full_address")
    private String fullAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private UserType type;
}
