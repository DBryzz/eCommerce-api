package com.dbryzz.ecoms.domain.dto;

import com.dbryzz.ecoms.domain.model.Category;
import com.dbryzz.ecoms.domain.model.User;
import lombok.Data;

@Data
public class ItemDTO {
    private Long itemId;
    private String itemName;
    private User itemSeller;
    private Category itemCategory;
    private Long itemUnitPrice;
    private double itemShippingCost;
    private Long itemQuantity;
    private String itemImageUrl;
    private double itemTotalPrice;
    private int itemCount = 10;
}
