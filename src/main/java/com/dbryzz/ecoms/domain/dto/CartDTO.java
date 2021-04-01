package com.dbryzz.ecoms.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {

    private List<ItemDTO> items;
    private double totalCartPrice;
    private double cartShippingPrice;
    private double cartItemsPrice;
    private int cartSize = 10;
    private int itemCount = 0;


   /* public boolean addItem(ProductDTO item) {
        return true;
    }

    public boolean removeItem(ProductDTO item) {
        return true;

    }

    public boolean updateItemQuantity(ProductDTO item, int quantity) {
        return true;

    }

    public boolean checkout() {
        return true;

    }*/
}
