package com.example.velotochka.services;

import com.example.velotochka.entities.Category;
import com.example.velotochka.entities.Product;
import com.example.velotochka.models.CategoryModel;
import com.example.velotochka.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategotyService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<CategoryModel> findAll() {
        return categoryRepository.findAll().stream().map(CategoryModel::toModel).collect(Collectors.toList());
    }
    public CategoryModel findById(Long id) {
        return CategoryModel.toModel(categoryRepository.findById(id).get());
    }
    public Long deleteById(Long id) {
        categoryRepository.deleteById(id);
        return id;
    }
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}
