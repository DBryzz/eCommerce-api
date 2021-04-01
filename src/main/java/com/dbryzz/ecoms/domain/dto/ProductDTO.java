package com.dbryzz.ecoms.domain.dto;

import com.dbryzz.ecoms.domain.model.Category;
import com.dbryzz.ecoms.domain.model.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ProductDTO {

    @NotBlank
    private Long productId;

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

    @NotBlank
    private String productImageUrl;


//    private List<User> buyers;

//    @JsonIgnore
    private User seller;

//    @JsonIgnore
    @NotBlank
    private Category productCategory;

    public ProductDTO() {
    }

    public ProductDTO(Long productId, String productName,
                      String productDescription, Long productPrice,
                      Long productQuantity, String productImageUrl,
                      User seller, Category productCategory) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productImageUrl = productImageUrl;
        this.seller = seller;
        this.productCategory = productCategory;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }
}
