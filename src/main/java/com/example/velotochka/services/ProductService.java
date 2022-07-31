package com.example.velotochka.services;

import com.example.velotochka.entities.Category;
import com.example.velotochka.entities.Image;
import com.example.velotochka.entities.Product;
import com.example.velotochka.models.ProductModel;
import com.example.velotochka.repositories.CategoryRepository;
import com.example.velotochka.repositories.ProductRepository;
import com.example.velotochka.specifications.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    /*public List<ProductModel> findProducts(MultiValueMap<String, String> features) {
        return features.isEmpty() ? findAllProducts() : findProductsByFeatures(features);
    }*/
    public List<ProductModel> findProducts(Specification<Product> specification, Pageable pageable) {
        return productRepository.findAll(specification, pageable).stream()
                .map(ProductModel::toModel)
                .collect(Collectors.toList());
    }
    public List<ProductModel> findAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductModel::toModel)
                .collect(Collectors.toList());
    }
    public ProductModel saveProduct(Product product, Set<MultipartFile> files) throws RuntimeException {
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

        if(files != null) {
            Set<Image> images = product.getImages();
            if (images == null) {
                images = new HashSet<>();
            }
            Set<Image> finalImages = images;
            files.forEach(file -> {
                try {
                    finalImages.add(new Image(file));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            product.setImages(images);
        } else {
            product.setImages(new HashSet<>());
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

    public List<ProductModel> findProductsByFeatures(MultiValueMap<String, String> features){
        List<Product> answer = null;
        for (Map.Entry<String, List<String>> entry : features.entrySet()) {
            String key = entry.getKey();
            for (String value : entry.getValue()) {
                List<Product> products = productRepository.findByFeatures_NameAndFeatures_Value(key, value);
                if (answer == null) { // this is true during first iteration
                    answer = products;
                } else {
                    answer.retainAll(products);
                }
            }
        }
        if (answer == null) {
            answer = new ArrayList<>();
        }
        return answer.stream()
                .map(ProductModel::toModel)
                .collect(Collectors.toList());
    }

    public List<ProductModel> findProducts(
            List<String> categories,
            List<String> minPrices,
            List<String> maxPrices,
            List<String> names,
            MultiValueMap<String, String> features,
            Pageable pageable) {
        Specification<Product> specification = Specification.where(null);
        for (String category : categories) {
            specification = specification.and(new ProductCategorySpecification(category));
        }
        for (String minPrice : minPrices) {
            specification = specification.and(new ProductMinPriceSpecification(minPrice));
        }
        for (String maxPrice : maxPrices) {
            specification = specification.and(new ProductMaxPriceSpecification(maxPrice));
        }
        for (String name : names) {
            specification = specification.and(new ProductNameSpecification(name));
        }
        for (Map.Entry<String, List<String>> entry : features.entrySet()) {
            String key = entry.getKey();
            for (String value : entry.getValue()) {
                specification = specification.and(new ProductFeatureSpecification(key, value));
            }
        }
        return productRepository.findAll(specification, pageable).stream()
                .map(ProductModel::toModel)
                .collect(Collectors.toList());
    }

}
