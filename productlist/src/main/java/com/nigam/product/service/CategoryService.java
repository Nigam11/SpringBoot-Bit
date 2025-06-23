package com.nigam.product.service;

import com.nigam.product.dto.CategoryDTO;
import com.nigam.product.entity.Category;
import com.nigam.product.mapper.CategoryMapper;
import com.nigam.product.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;


    // ✅ Constructor injection


    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        // ✅ Convert DTO → Entity
        Category category = CategoryMapper.toCategoryEntity(categoryDTO);

        // ✅ Save entity
        category = categoryRepository.save(category);

        // ✅ Convert saved entity → DTO and return
        return CategoryMapper.toCategoryDTO(category);
    }
}
