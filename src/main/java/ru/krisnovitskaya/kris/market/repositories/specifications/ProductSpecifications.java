package ru.krisnovitskaya.kris.market.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.krisnovitskaya.kris.market.entities.Product;

public class ProductSpecifications {
    public static Specification<Product> priceGreaterOrEqualsThan(int minPrice){
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> priceLesserOrEqualsThan(int maxPrice){
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<Product> titleLike(String titlePart){
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }
}
