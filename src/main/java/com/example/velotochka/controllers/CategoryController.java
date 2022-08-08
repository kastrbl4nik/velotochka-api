package com.example.velotochka.controllers;

import com.example.velotochka.entities.Category;
import com.example.velotochka.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.function.Supplier;

@CrossOrigin
@RestController
@RequestMapping("categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public ResponseEntity<Object> getAll() {
        return createResponseEntity(() -> categoryService.getAllNames());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        return createResponseEntity(() -> categoryService.getNameById(id));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Category category) {
        return createResponseEntity(() -> categoryService.save(category));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        return createResponseEntity(() -> categoryService.deleteById(id));
    }

    private ResponseEntity<Object> createResponseEntity(Supplier<Object> supplier) {
        try {
            return ResponseEntity.ok(supplier.get());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
