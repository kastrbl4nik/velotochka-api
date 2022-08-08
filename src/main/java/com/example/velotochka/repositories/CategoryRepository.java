package com.example.velotochka.repositories;

import com.example.velotochka.entities.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByName(String name);
    List<Category> findAll();
}
