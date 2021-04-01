package com.dbryzz.ecoms.domain.model;

import com.dbryzz.ecoms.config.DatePrefixedSequenceIdGenerator;
import com.dbryzz.ecoms.config.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.dbryzz.ecoms.domain.constant.OrderStatus;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @GenericGenerator(
            name = "order_seq",
            strategy = "org.thoughts.on.java.generators.DatePrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = DatePrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "900000000"),
                    @Parameter(name = DatePrefixedSequenceIdGenerator.DATE_FORMAT_PARAMETER, value = "%ty-%tm-%td_"),
                    @Parameter(name = DatePrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    @Column(name = "order_id")
    private String orderId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "item_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> orderItems;

    @JsonIgnore
    @NotBlank
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "buyer")
    private User buyer;

    @NotBlank
    @Column(name = "placed_on")
    private LocalDateTime placedOn;

    @NotBlank
    @Column(name = "buyer_phone_num")
    private String buyerPhoneNum;

    @NotBlank
    @Column(name = "total_price")
    private double totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;




    /*@NotBlank
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productCategory")
    @JoinTable(name = "items")
    private List<Item> items;*/

//    @Transient




}
