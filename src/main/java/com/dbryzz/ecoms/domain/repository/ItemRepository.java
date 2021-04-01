package com.dbryzz.ecoms.domain.repository;

import com.dbryzz.ecoms.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
