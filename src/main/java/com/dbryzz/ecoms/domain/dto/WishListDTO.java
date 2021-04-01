package com.gg.ecoms.domain.dto;

import com.gg.ecoms.domain.model.Product;
import com.gg.ecoms.domain.model.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class WishListDTO {

    private Long wishListId;

    @NotBlank
    private String wishListName;

    private List<Product> wishListItems;
    private User buyer;
}
