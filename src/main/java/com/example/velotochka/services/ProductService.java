package com.example.velotochka.services;

import com.example.velotochka.entities.ProductEntity;
import com.example.velotochka.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public Iterable<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    public ProductEntity getById(Long id) {
        return productRepository.findById(id).get();
    }
    public ProductEntity add(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    public Long remove(Long id) {
        productRepository.deleteById(id);
        return id;
    }
}
