package com.gg.ecom.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gg.ecom.model.Category;
import com.gg.ecom.model.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PostProductDTO {

    @NotBlank
    @Size(max = 20)
    private String productName;

    @NotBlank
    @Size(max = 50)
    private String productDescription;

    @NotBlank
    private Long productPrice;

    @NotBlank
    @Size(max = 1000)
    private Long productQuantity;

   /* @NotBlank
    private String productImageUrl;*/
            /*
                {
                    "productDescription": "Just a robe",
                    "productName": "gown",
                    "productPrice": 15000,
                    "productQuantity": 5
                }
            */

   /* private List<User> buyers;

    @JsonIgnore
    private User seller;
    */

    @JsonIgnore
    @NotBlank
    private Category productCategory;

    public PostProductDTO() {
    }

    public PostProductDTO(String productName, String productDescription,
                          Long productPrice, Long productQuantity,
                          Category productCategory) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productCategory = productCategory;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public Long getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Long productQuantity) {
        this.productQuantity = productQuantity;
    }

/*
    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }
*/

    /*public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
*/
    public Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }

}
