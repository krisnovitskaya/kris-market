package ru.krisnovitskaya.kris.market.utils;

import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;
import ru.krisnovitskaya.kris.market.entities.Product;
import ru.krisnovitskaya.kris.market.repositories.specifications.ProductSpecifications;

import java.util.List;
import java.util.stream.Collectors;


@Getter
public class ProductFilter {
    private Specification<Product> spec;

    /**
     * Create Specification<Product> with input params (title, max/min price, categories)
     * @param params
     */

    public ProductFilter(MultiValueMap<String, String> params) {
            spec = Specification.where(null);


        String filterTitle = params.getFirst("title");
        if (filterTitle != null && !filterTitle.isBlank()) {
            spec = spec.and(ProductSpecifications.titleLike(filterTitle));
        }


        if (params.getFirst("min_price") != null && !params.getFirst("min_price").isBlank()) {
            Integer minPrice = Integer.parseInt(params.getFirst("min_price"));
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }

        if (params.getFirst("max_price") != null && !params.getFirst("max_price").isBlank()) {
            Integer maxPrice = Integer.parseInt(params.getFirst("max_price"));
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(maxPrice));
        }


        if (params.containsKey("categories")){
            List<String> categories = params.get("categories");
            List<Long> categoriesIDs = categories.stream().map(s -> Long.parseLong(s)).collect(Collectors.toList());
            spec = spec.and(ProductSpecifications.haveCategory(categoriesIDs));
        }
    }
}
