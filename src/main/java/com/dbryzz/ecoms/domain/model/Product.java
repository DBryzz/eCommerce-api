package com.dbryzz.ecoms.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "user_email")
        })
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @NotBlank
    @Column(name = "name")
    @Size(max = 20)
    private String productName;

    @NotBlank
    @Size(max = 50)
    @Column(name = "description")
    private String productDescription;

    @NotBlank
    @Column(name = "Price")
    private Long productPrice;

    @NotBlank
    @Size(max = 1000)
    @Column(name = "available_quantity")
    private Long productAvailableQuantity;

    @NotBlank
    @Column(name = "image_url")
    private String productImageUrl;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_buyer",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<User> buyers;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "product_seller")
//    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User seller;

    @JsonIgnore
    @NotBlank
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "product_category")
    private Category productCategory;

    @NotBlank
    private LocalDateTime createdDate;

    @NotBlank
    private LocalDateTime modifiedDate;

    @OneToOne(mappedBy = "itemProduct")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private WishList wishList;


}
