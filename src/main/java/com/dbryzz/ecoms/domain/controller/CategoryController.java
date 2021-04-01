package com.dbryzz.ecoms.domain.controller;

import com.dbryzz.ecoms.domain.dto.CategoryDTO;
import com.dbryzz.ecoms.domain.dto.CategoryPostDTO;
import com.dbryzz.ecoms.domain.service.CategoryService;
import com.dbryzz.ecoms.domain.dto.MessageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping("")
    public ResponseEntity<?> addCategory (@RequestBody CategoryPostDTO categoryDTO) {
        categoryService.addCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MessageResponseDTO("New Category Added Successfully"));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getACategory(@PathVariable("categoryId") Long catId) {
        return ResponseEntity.ok(categoryService.getCategory(catId));
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> removeCategory(@PathVariable("categoryId") Long catId) {
        categoryService.removeCategory(catId);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new MessageResponseDTO("Successfully deleted category category "+ catId));
    }

    @PutMapping("/edit/{categoryId}")
    public ResponseEntity<?> editCategory(@PathVariable("categoryId") Long catId, CategoryPostDTO categoryDTO) {
        categoryService.updateCategory(catId, categoryDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new MessageResponseDTO("Category Successfully Updated"));
    }

}
