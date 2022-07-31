package com.example.velotochka.specifications;

import com.example.velotochka.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class ProductFeatureSpecification implements Specification<Product> {
    private final String name;
    private final String value;

    public ProductFeatureSpecification(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (null == name || null == value) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }

        Join<Object, Object> join = root.join("features");
        return criteriaBuilder.and(
                criteriaBuilder.like(join.get("name"), name),
                criteriaBuilder.like(join.get("value"), value)
        );
    }
}
