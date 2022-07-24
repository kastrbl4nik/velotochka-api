package com.example.velotochka.repositories;

import com.example.velotochka.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();
    List<Product> findAll(Specification<Product> specification, Pageable pageable);
    List<Product> findByCategoryName(String category);
    List<Product> findByFeatures_NameAndFeatures_Value(String name, String value);
}
