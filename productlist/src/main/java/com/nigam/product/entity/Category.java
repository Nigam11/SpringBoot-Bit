package com.nigam.product.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private  String name;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Product> products=new ArrayList<>();

}
