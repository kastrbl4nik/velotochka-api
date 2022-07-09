package com.example.velotochka.controllers;

import com.example.velotochka.entities.Category;
import com.example.velotochka.entities.Product;
import com.example.velotochka.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.function.Supplier;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity getProducts() {
        return createResponseEntity(() -> productService.findAllProducts());
    }

    @GetMapping("{id}")
    public ResponseEntity getProductById(@PathVariable Long id) {
        return createResponseEntity(() -> productService.findProductById(id));
    }

    @PostMapping
    public ResponseEntity saveProduct(@RequestBody Product product) {
        return createResponseEntity(() -> productService.saveProduct(product));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteProductById(@PathVariable Long id) {
        return createResponseEntity(() -> productService.deleteProductById(id));
    }

    @GetMapping("/categories")
    public ResponseEntity getProductTypes() {
        return createResponseEntity(() -> productService.findAllCategories());
    }

    @PostMapping("/categories")
    public ResponseEntity saveCategory(@RequestBody Category category) {
        return createResponseEntity(() -> productService.saveCategory(category));
    }

    @GetMapping("/categories/{category}")
    public ResponseEntity getCategoryProducts(@PathVariable String category) {
        return createResponseEntity(() -> productService.findByCategory(category));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity deleteCategoryById(@PathVariable Long id) {
        return createResponseEntity(() -> productService.deleteCategoryById(id));
    }

    private ResponseEntity<Object> createResponseEntity(Supplier<Object> supplier) {
        try {
            return ResponseEntity.ok(supplier.get());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

}
