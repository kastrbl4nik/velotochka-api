package com.example.velotochka.services;

import com.example.velotochka.entities.Product;
import com.example.velotochka.models.ProductModel;
import com.example.velotochka.repositories.CategoryRepository;
import com.example.velotochka.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    public List<ProductModel> findAll() {
        return productRepository.findAll().stream().map(ProductModel::toModel).collect(Collectors.toList());
    }
    public Product save(Product product, String categoryName) {
        product.setCategory(categoryRepository.findByName(categoryName));
        return productRepository.save(product);
    }
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }
    public Long deleteById(Long id) {
        productRepository.deleteById(id);
        return id;
    }
}
