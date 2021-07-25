package com.jdc.onlineshopping.repository;

import com.jdc.onlineshopping.domain.OrderSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tiendao
 */
public interface OrderSnapshotRepository extends JpaRepository<OrderSnapshot, Long> {
}
