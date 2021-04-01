package com.dbryzz.ecoms.domain.service;

import com.dbryzz.ecoms.domain.dto.CategoryDTO;
import com.dbryzz.ecoms.domain.dto.CategoryPostDTO;

import java.util.List;

public interface CategoryService {
    public List<CategoryDTO> getCategories();
    public CategoryDTO getCategory(Long id);
    public void addCategory(CategoryPostDTO categoryDTO);
    public void updateCategory(Long id, CategoryPostDTO categoryDTO);
    public void removeCategory(Long id);

}
