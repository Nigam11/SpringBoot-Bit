package com.nigam.product.controller;

import com.nigam.product.exception.CategoryAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.nigam.product.dto.CategoryDTO;
import com.nigam.product.service.CategoryService;

import lombok.AllArgsConstructor;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO) {

            CategoryDTO savedCategory = categoryService. createCategory (categoryDTO) ;
            return ResponseEntity.status(HttpStatus.CREATED) .body (savedCategory);

        //return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 204
    }

    // ✅ NEW: Update Category by ID
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryDTO categoryDTO) {
        return new ResponseEntity<>(categoryService.updateCategory(id, categoryDTO), HttpStatus.OK);
    }
}
