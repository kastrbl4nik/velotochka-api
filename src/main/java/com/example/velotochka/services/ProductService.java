package com.example.velotochka.services;

import com.example.velotochka.entities.Category;
import com.example.velotochka.entities.Product;
import com.example.velotochka.models.ProductModel;
import com.example.velotochka.repositories.CategoryRepository;
import com.example.velotochka.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    public List<ProductModel> findAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductModel::toModel)
                .collect(Collectors.toList());
    }
    public ProductModel saveProduct(Product product) throws RuntimeException {
        Category category = product.getCategory();
        if (category != null) {
            Category existingCategory = categoryRepository.findByName(category.getName());
            if (existingCategory != null) {
                product.setCategory(existingCategory);
            } else {
                categoryRepository.save(product.getCategory());
            }
        } else {
            throw new IllegalArgumentException("Category cannot be null.");
        }
        return ProductModel.toModel(productRepository.save(product));
    }
    public ProductModel findProductById(Long id) {
        return ProductModel.toModel(productRepository.findById(id).orElse(new Product()));
    }
    public Long deleteProductById(Long id) {
        productRepository.deleteById(id);
        return id;
    }
    public List<ProductModel> findByCategory(String category) {
        return productRepository.findByCategoryName(category).stream()
                .map(ProductModel::toModel)
                .collect(Collectors.toList());
    }
    public List<String> findAllCategories() {
        return categoryRepository.findAll().stream().map(Category::getName).collect(Collectors.toList());
    }
    public String findCategoryById(Long id) {
        return categoryRepository.findById(id).get().getName();
    }
    public Long deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
        return id;
    }
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

}
