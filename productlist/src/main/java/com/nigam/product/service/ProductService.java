package com.nigam.product.service;

import com.nigam.product.dto.CategoryDTO;
import com.nigam.product.dto.ProductDTO;
import com.nigam.product.entity.Category;
import com.nigam.product.entity.Product;
import com.nigam.product.exception.CategoryNotFoundException;
import com.nigam.product.mapper.CategoryMapper;
import com.nigam.product.mapper.ProductMapper;
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

    // ✅ CREATE new product
    public ProductDTO createProduct(ProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category id " + productDTO.getCategoryId() + " not found"));

        Product product = ProductMapper.toProductEntity(productDTO, category);
        product = productRepository.save(product);
        return ProductMapper.toProductDTO(product);
    }

    // ✅ GET all products
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toProductDTO)
                .toList();
    }

    // ✅ GET product by ID
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product id " + id + " not found"));
        return ProductMapper.toProductDTO(product);
    }

    // ✅ UPDATE product
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product id " + id + " not found"));

        // ✅ Update category if provided
        if (productDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new CategoryNotFoundException(
                            "Category id " + productDTO.getCategoryId() + " not found"));
            existingProduct.setCategory(category);
        }

        // ✅ Update other fields
        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());

        existingProduct = productRepository.save(existingProduct);
        return ProductMapper.toProductDTO(existingProduct);
    }

    // ✅ DELETE product
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product id " + id + " not found");
        }
        productRepository.deleteById(id);
    }

    // ✅ GET all categories as DTOs
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryMapper::toCategoryDTO)
                .toList();
    }
}
