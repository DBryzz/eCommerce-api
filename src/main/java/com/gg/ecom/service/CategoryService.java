package com.gg.ecom.service;

import com.gg.ecom.dto.CategoryDTO;
import com.gg.ecom.dto.PostCategoryDTO;

import java.util.List;

public interface CategoryService {
    public List<CategoryDTO> getCategories();
    public CategoryDTO getCategory(Long id);
    public void addCategory(PostCategoryDTO categoryDTO);
    public void updateCategory(Long id, PostCategoryDTO categoryDTO);
    public void removeCategory(Long id);

}
