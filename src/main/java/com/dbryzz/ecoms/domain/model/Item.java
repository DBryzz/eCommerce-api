package com.dbryzz.ecoms.domain.model;

import com.dbryzz.ecoms.domain.model.Category;
import com.dbryzz.ecoms.domain.model.Product;
import com.dbryzz.ecoms.domain.model.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product itemProduct;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_price")
    private Long itemUnitPrice;

    @Column(name = "ordered_qty")
    private Long itemQuantity;

    @Transient
    private double itemShippingCost;

    @Transient
    private double itemTotalPrice;

    @Transient
    private User itemSeller;

    @Transient
    private Category itemCategory;


    @Transient
    private String itemImageUrl;


    @Transient
    private int itemCount = 10;

    /*@Transient
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "item_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Order> orderItems;*/
}
