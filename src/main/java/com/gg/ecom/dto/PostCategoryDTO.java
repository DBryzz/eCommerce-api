package com.gg.ecom.dto;

import com.gg.ecom.model.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class PostCategoryDTO {

    @NotBlank
    @Size(max = 20)
    private String categoryName;

    @NotBlank
    @Size(max = 100)
    private String categoryDescription;

    public PostCategoryDTO() {
    }

    public PostCategoryDTO(String categoryName, String categoryDescription) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
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


}
