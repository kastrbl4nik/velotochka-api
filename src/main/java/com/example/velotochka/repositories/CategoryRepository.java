package com.example.velotochka.repositories;

import com.example.velotochka.entities.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findByName(String name);
    List<Category> findAll();
}
