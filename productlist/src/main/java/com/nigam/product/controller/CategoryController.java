package com.nigam.product.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.nigam.product.dto.CategoryDTO;
import com.nigam.product.service.CategoryService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory (@RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.createCategory (categoryDTO), HttpStatus.CREATED) ;
    }

}
