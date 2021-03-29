package com.gg.ecom.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gg.ecom.model.Product;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class CategoryDTO {

    @NotBlank
    private Long categoryId;

    @NotBlank
    @Size(max = 20)
    private String categoryName;

    @NotBlank
    @Size(max = 100)
    private String categoryDescription;

    // private List<Product> products;

    public CategoryDTO() {
    }

    public CategoryDTO(Long categoryId, String categoryName, String categoryDescription) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    /*public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }*/
}
