package com.jdc.onlineshopping.repository;

import com.jdc.onlineshopping.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author tiendao on 22/07/2021
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String userName);
}
