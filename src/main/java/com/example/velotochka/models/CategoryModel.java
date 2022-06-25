package com.example.velotochka.models;

import com.example.velotochka.entities.Category;
import com.example.velotochka.entities.Product;

public class CategoryModel {
    private String name;
    public static CategoryModel toModel(Category category) {
        return new CategoryModel(
                category.getName()
        );
    }

    public CategoryModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
