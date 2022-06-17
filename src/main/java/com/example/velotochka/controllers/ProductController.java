package com.example.velotochka.controllers;

import com.example.velotochka.entities.ProductEntity;
import com.example.velotochka.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(productService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(productService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping
    public ResponseEntity add(@RequestBody ProductEntity productEntity) {
        try {
            return ResponseEntity.ok(productService.add(productEntity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(productService.remove(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
