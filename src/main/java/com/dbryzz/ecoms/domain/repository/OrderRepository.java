package com.dbryzz.ecoms.domain.repository;

import com.dbryzz.ecoms.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
