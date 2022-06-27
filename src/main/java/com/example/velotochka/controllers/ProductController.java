package com.example.velotochka.controllers;

import com.example.velotochka.entities.Category;
import com.example.velotochka.entities.Product;
import com.example.velotochka.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public ResponseEntity getProducts() {
        try {
            return ResponseEntity.ok(productService.findAllProducts());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
    @GetMapping("{id}")
    public ResponseEntity getProductById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(productService.findProductById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
    @PostMapping
    public ResponseEntity saveProduct(@RequestBody Product product) {
        try {
            return ResponseEntity.ok(productService.saveProduct(product));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteProductById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(productService.deleteProductById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
    @GetMapping("/categories")
    public ResponseEntity getProductTypes() {
        try {
            return ResponseEntity.ok(productService.findAllCategories());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
    @PostMapping("/categories")
    public ResponseEntity saveCategory(@RequestBody Category category) {
        try {
            return ResponseEntity.ok(productService.saveCategory(category));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/categories/{category}")
    public ResponseEntity getCategoryProducts(@PathVariable String category) {
        try {
            return ResponseEntity.ok(productService.findByCategory(category));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity deleteCategoryById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(productService.deleteCategoryById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
