package com.dbryzz.ecoms.domain.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategoryPostDTO {

    @NotBlank
    @Size(max = 20)
    private String categoryName;

    @NotBlank
    @Size(max = 100)
    private String categoryDescription;

    public CategoryPostDTO() {
    }

    public CategoryPostDTO(String categoryName, String categoryDescription) {
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
