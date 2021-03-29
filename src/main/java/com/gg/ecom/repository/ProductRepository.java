package com.gg.ecom.repository;

import com.gg.ecom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
    Iterable<Product> findByProductName(String productName);
    Iterable<Product> findByProductPrice(Long Price);

}
