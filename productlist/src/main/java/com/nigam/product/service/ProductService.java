package com.nigam.product.service;

import com.nigam.product.dto.ProductDTO;
import com.nigam.product.dto.CategoryDTO;
import com.nigam.product.entity.Category;
import com.nigam.product.entity.Product;
import com.nigam.product.mapper.ProductMapper;
import com.nigam.product.mapper.CategoryMapper;
import com.nigam.product.repository.CategoryRepository;
import com.nigam.product.repository.ProductRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // ✅ Create new product
    public ProductDTO createProduct(ProductDTO productDTO) {
        // Fetch the category
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Map DTO → Entity
        Product product = ProductMapper.toProductEntity(productDTO, category);

        // Save product
        product = productRepository.save(product);

        // Map Entity → DTO and return
        return ProductMapper.toProductDTO(product);
    }

    // ✅ Get all categories as DTO
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryMapper::toCategoryDTO)
                .toList();
    }

    // ✅ NEW: Update an existing product
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        // Fetch existing product or throw error
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // ✅ Fetch the new category if passed
        if (productDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existingProduct.setCategory(category);
        }

        // ✅ Update other product fields
        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());

        // ✅ Save the updated product
        existingProduct = productRepository.save(existingProduct);

        // ✅ Convert to DTO and return
        return ProductMapper.toProductDTO(existingProduct);
    }
}
