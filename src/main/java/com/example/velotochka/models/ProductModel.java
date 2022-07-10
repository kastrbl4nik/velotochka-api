package com.example.velotochka.models;

import com.example.velotochka.entities.Image;
import com.example.velotochka.entities.Product;
import com.example.velotochka.entities.Feature;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductModel {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String category;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date created;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date updated;
    private Map<String, Object> features;
    private Set<byte[]> images;
    public static ProductModel toModel(Product product) {
        return new ProductModel(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getCategory().getName(),
                product.getCreated(),
                product.getUpdated(),
                product.getFeaturesMap(),
                product.getImages().stream()
                        .map(Image::getImage)
                        .collect(Collectors.toSet())
        );
    }

    public ProductModel(Long id, String name, Double price, String description, String category, Date created, Date updated, Map<String, Object> features, Set<byte[]> images) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.created = created;
        this.updated = updated;
        this.features = features;
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Map<String, Object> getFeatures() {
        return features;
    }

    public void setFeatures(Map<String, Object> features) {
        this.features = features;
    }

    public Set<byte[]> getImages() {
        return images;
    }

    public void setImages(Set<byte[]> images) {
        this.images = images;
    }
}
