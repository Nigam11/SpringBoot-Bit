package com.nigam.product.service;

import com.nigam.product.dto.CategoryDTO;
import com.nigam.product.entity.Category;
import com.nigam.product.exception.CategoryAlreadyExistsException;
import com.nigam.product.mapper.CategoryMapper;
import com.nigam.product.repository.CategoryRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * Create a new category.
     */
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Optional<Category> optionalCategory = categoryRepository.findByName(categoryDTO. getName ());
        if(optionalCategory.isPresent()){
            throw new CategoryAlreadyExistsException("Category "
                    + categoryDTO. getName() +" already exists!");
        }
        // Convert DTO → Entity
        Category category = CategoryMapper.toCategoryEntity(categoryDTO);

        // Save entity
        category = categoryRepository.save(category);

        // Convert saved entity → DTO
        return CategoryMapper.toCategoryDTO(category);
    }

    /**
     * Get all categories as a list of CategoryDTO.
     */
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()  // Fetch all entities
                .stream()     // Convert list to Stream
                .map(CategoryMapper::toCategoryDTO) // Map each entity to DTO
                .collect(Collectors.toList());      // Collect back into a list
    }
    public CategoryDTO getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(CategoryMapper::toCategoryDTO)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);

    }
    // inside CategoryService
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        // 1. Fetch existing category
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // 2. Update fields
        existingCategory.setName(categoryDTO.getName());

        // 3. Save updated entity
        existingCategory = categoryRepository.save(existingCategory);

        // 4. Return as DTO
        return CategoryMapper.toCategoryDTO(existingCategory);
    }


}
