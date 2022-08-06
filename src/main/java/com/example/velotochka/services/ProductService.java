package com.example.velotochka.services;

import com.example.velotochka.entities.Category;
import com.example.velotochka.entities.Image;
import com.example.velotochka.entities.Product;
import com.example.velotochka.models.ProductModel;
import com.example.velotochka.repositories.ProductRepository;
import com.example.velotochka.specifications.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public ProductModel saveProduct(Product product, Set<MultipartFile> files) throws RuntimeException {
        Category category = product.getCategory();
        if (category != null) {
            boolean categoryAlreadyExists = (null != categoryService.findByName(category.getName()));
            if (categoryAlreadyExists) {
                product.setCategory(category);
            } else {
                categoryService.save(product.getCategory());
            }
        } else {
            throw new IllegalArgumentException("Category cannot be null.");
        }

        Set<Image> images = Optional.ofNullable(product.getImages()).orElse(new HashSet<>());
        if (files != null) {
            files.forEach(file -> {
                try {
                    images.add(new Image(file));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        product.setImages(images);

        return ProductModel.toModel(productRepository.save(product));
    }

    public ProductModel findProductById(Long id) {
        return ProductModel.toModel(productRepository.findById(id).orElse(new Product()));
    }

    public Long deleteProductById(Long id) {
        productRepository.deleteById(id);
        return id;
    }

    public List<ProductModel> findProducts(
            List<String> categories,
            List<String> minPrices,
            List<String> maxPrices,
            List<String> names,
            MultiValueMap<String, String> features,
            Pageable pageable) {

        Specification<Product> specification = Specification.where(null);
        specification = fillSpecifiedSpecifications(specification, categories, ProductCategorySpecification.class);
        specification = fillSpecifiedSpecifications(specification, minPrices,  ProductMinPriceSpecification.class);
        specification = fillSpecifiedSpecifications(specification, maxPrices,  ProductMaxPriceSpecification.class);
        specification = fillSpecifiedSpecifications(specification, names,      ProductNameSpecification.class);

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

    private Specification<Product> fillSpecifiedSpecifications(
            Specification<Product> specification,
            List<String> values,
            Class<? extends Specification<Product>> specificationClass
    ) {
        for (String value : values) {
            try {
                specification = specification.and(specificationClass.getConstructor(String.class).newInstance(value));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return specification;
    }

}
