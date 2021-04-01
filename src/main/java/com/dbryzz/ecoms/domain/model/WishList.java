package com.dbryzz.ecoms.domain.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@Table(name = "wishlist")
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private Long wishListId;

    @NotBlank
    @Column(name = "wishlist_name", unique = true)
    private String wishListName;

    //    @OneToOne(fetch = FetchType.LAZY, mappedBy = "wishList", cascade = CascadeType.ALL)
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Product> wishListItems;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_wishlist")
//    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User buyer;
}
