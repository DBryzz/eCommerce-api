package com.dbryzz.ecoms.domain.repository;

import com.dbryzz.ecoms.domain.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    Iterable<WishList> findAllByBuyer_UserId(String userId);
    Optional<WishList> findWishListByBuyer_UserIdAndWishListName(String userId, String wishListName);
}
