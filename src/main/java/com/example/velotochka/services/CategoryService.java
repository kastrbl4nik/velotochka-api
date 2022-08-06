package com.example.velotochka.services;

import com.example.velotochka.entities.Category;
import com.example.velotochka.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    public List<String> getAllNames() {
        return categoryRepository.findAll().stream()
                .map(Category::getName)
                .collect(Collectors.toList());
    }

    public String getNameById(Long id) {
        return categoryRepository.findById(id).get().getName();
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Long deleteById(Long id) {
        categoryRepository.deleteById(id);
        return id;
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

}
