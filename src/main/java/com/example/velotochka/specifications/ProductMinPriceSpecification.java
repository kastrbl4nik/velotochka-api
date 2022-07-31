package com.example.velotochka.specifications;

import com.example.velotochka.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProductMinPriceSpecification implements Specification<Product> {
    private final String minPrice;

    public ProductMinPriceSpecification(String minPrice) {
        this.minPrice = minPrice;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (null == minPrice) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }

        return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }
}
