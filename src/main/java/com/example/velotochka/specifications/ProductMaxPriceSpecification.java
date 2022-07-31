package com.example.velotochka.specifications;

import com.example.velotochka.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProductMaxPriceSpecification implements Specification<Product> {
    private final String maxPrice;

    public ProductMaxPriceSpecification(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (null == maxPrice) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }

        return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }
}
