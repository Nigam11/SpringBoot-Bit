package com.nigam.product.mapper;

import com.nigam.product.dto.ProductDTO;
import com.nigam.product.entity.Product;
import com.nigam.product.entity.Category;

public class ProductMapper {

    // Entity → DTO
    public static ProductDTO toProductDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory().getId()
        );
    }

    // DTO → Entity
    public static Product toProductEntity(ProductDTO productDTO, Category category) {
        Product product = new Product();
        //product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);  // Set the associated Category entity
        return product;
    }
}
