package com.gg.ecom.service.impl;

import com.gg.ecom.dto.CategoryDTO;
import com.gg.ecom.dto.PostCategoryDTO;
import com.gg.ecom.exception.ResourceNotFoundException;
import com.gg.ecom.model.Category;
import com.gg.ecom.repository.CategoryRepository;
import com.gg.ecom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return loadCategoryDTOS(categories);
    }

    @Override
    public CategoryDTO getCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            throw new ResourceNotFoundException("Category Not Found: CategoryId - " +id );
        }
        return copyCategorytoCatergoryDTO(category.get(), new CategoryDTO());
    }

    @Override
    public void addCategory(PostCategoryDTO categoryDTO) {
        Category category = copyCategoryDTOtoCategory(categoryDTO, new Category());
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Long id, PostCategoryDTO categoryDTO) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            throw new ResourceNotFoundException("Category Not Found: CategoryId - " +id );
        }
        Category categoryUpdate = copyCategoryDTOtoCategory(categoryDTO, category.get());
        categoryRepository.save(categoryUpdate);
    }

    @Override
    public void removeCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            throw new ResourceNotFoundException("Category Not Found: CategoryId - " +id );
        }
        categoryRepository.deleteById(id);
    }



    public List<CategoryDTO> loadCategoryDTOS(Iterable<Category> categories) {
        List<CategoryDTO> catergoryDTOS = new ArrayList<>();
        for (Category category : categories) {
            catergoryDTOS.add(copyCategorytoCatergoryDTO(category, new CategoryDTO()));
        }

        return catergoryDTOS;

    }

    private CategoryDTO copyCategorytoCatergoryDTO(Category category, CategoryDTO categoryDTO) {
        categoryDTO.setCategoryId(category.getCategoryId());
        categoryDTO.setCategoryName(category.getCategoryName());
        categoryDTO.setCategoryDescription(category.getCategoryDescription());


        return categoryDTO;
    }

    private Category copyCategoryDTOtoCategory(PostCategoryDTO categoryDTO, Category category) {
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());

        return category;
    }
}
