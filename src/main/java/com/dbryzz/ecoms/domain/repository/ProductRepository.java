package com.dbryzz.ecoms.domain.repository;

import com.dbryzz.ecoms.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
    Iterable<Product> findByProductName(String productName);
    Iterable<Product> findByProductPrice(Long Price);
    Iterable<Product> findByProductNameOrderByProductNameAsc(String productName);

    Iterable<Product> findAllBySellerUserId(String userId);
    Iterable<Product> findAllBySellerUserIdOrderByProductNameAsc(String userId);
    Iterable<Product> findAllBySellerUserIdOrderByProductNameDesc(String userId);
    Iterable<Product> findAllBySellerUserIdOrderByProductCategoryAsc(String userId);
    Iterable<Product> findAllBySellerUserIdOrderByProductCategoryDesc(String userId);
    Iterable<Product> findAllBySellerUserIdOrderByProductPriceAsc(String userId);
    Iterable<Product> findAllBySellerUserIdOrderByProductPriceDesc(String userId);
    Iterable<Product> findAllBySellerUserIdOrderByCreatedDateAsc(String userId);
    Iterable<Product> findAllBySellerUserIdOrderByCreatedDateDesc(String userId);
    Iterable<Product> findAllBySellerUserIdOrderByProductAvailableQuantityAsc(String userId);
    Iterable<Product> findAllBySellerUserIdOrderByProductAvailableQuantityDesc(String userId);
    Iterable<Product> findAllBySellerUserIdOrderByModifiedDateDesc(String userId);
    Iterable<Product> findAllBySellerUserIdOrderByModifiedDateAsc(String userId);



    Iterable<Product> findAllByProductCategoryCategoryId(Long categoryId);


    Iterable<Product> findAllByWishListWishListId(Long wishListId);



    //    @Query(value = "SELECT * FROM Product where productPrice between :priceLowerBound and :priceUpperBound order by productPrice ASC ")
    Iterable<Product> findAllByProductPriceBetweenOrderByProductPriceAsc(Long priceLowerBound, Long priceUpperBound);

}
