package com.example.velotochka.controllers;

import com.example.velotochka.entities.Category;
import com.example.velotochka.entities.Product;
import com.example.velotochka.services.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    /*@GetMapping
    public ResponseEntity getProducts(
            //@Parameter(explode = Explode.FALSE, style = ParameterStyle.FORM)
            @RequestParam(required = false) MultiValueMap<String, String> features){
        return createResponseEntity(() -> productService.findProducts(features));
    }*/

    @GetMapping
    public ResponseEntity getProducts(
            @RequestParam(name = "category", defaultValue = "") List<String> categories,
            @RequestParam(name = "price>",   defaultValue = "") List<String> minPrices,
            @RequestParam(name = "price<",   defaultValue = "") List<String> maxPrices,
            @RequestParam(name = "name",     defaultValue = "") List<String> names,
            @RequestParam(required = false,  defaultValue = "") MultiValueMap<String, String> features,
            Pageable pageable
    ) {
        features.remove("category");
        features.remove("price>");
        features.remove("price<");
        features.remove("name");

        return createResponseEntity(() -> productService.findProducts(categories, minPrices, maxPrices, names, features, pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity getProductById(@PathVariable Long id) {
        return createResponseEntity(() -> productService.findProductById(id));
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity saveProduct(@RequestParam(name = "body") String productJSON, @RequestParam(required = false, name = "images") Set<MultipartFile> files) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = objectMapper.readValue(productJSON, Product.class);
        return createResponseEntity(() -> productService.saveProduct(product, files));
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
    public ResponseEntity getCategoryProducts(
            @PathVariable String category,
            @RequestParam(required = false) MultiValueMap<String, String> features) {
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
