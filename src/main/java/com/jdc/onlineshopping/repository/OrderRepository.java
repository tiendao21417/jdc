package com.jdc.onlineshopping.repository;

import com.jdc.onlineshopping.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tiendao on 24/07/2021
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
