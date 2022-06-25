package com.example.velotochka.controllers;

import com.example.velotochka.entities.Category;
import com.example.velotochka.entities.Product;
import com.example.velotochka.services.ProductService;
import com.example.velotochka.services.CategotyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategotyService categotyService;
    @GetMapping
    public ResponseEntity getProducts() {
        try {
            return ResponseEntity.ok(productService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
    @GetMapping("{id}")
    public ResponseEntity getProductById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(productService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
    @PostMapping
    public ResponseEntity saveProduct(@RequestBody Product product, @RequestParam String category) {
        try {
            return ResponseEntity.ok(productService.save(product, category));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteProductById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(productService.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
    @GetMapping("/categories")
    public ResponseEntity getProductTypes() {
        try {
            return ResponseEntity.ok(categotyService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
    @PostMapping("/categories")
    public ResponseEntity saveCategory(@RequestBody Category category) {
        try {
            return ResponseEntity.ok(categotyService.save(category));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
