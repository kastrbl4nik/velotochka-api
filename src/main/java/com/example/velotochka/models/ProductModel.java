package com.example.velotochka.models;

import com.example.velotochka.entities.Product;

public class ProductModel {
    private Long id;
    private String name;
    private String category;
    public static ProductModel toModel(Product product) {
        return new ProductModel(
                product.getId(),
                product.getName(),
                product.getCategory().getName()
        );
    }

    public ProductModel(Long id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
