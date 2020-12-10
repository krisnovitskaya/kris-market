package ru.krisnovitskaya.kris.market.utils;

import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;
import ru.krisnovitskaya.kris.market.entities.Product;
import ru.krisnovitskaya.kris.market.repositories.specifications.ProductSpecifications;

import java.util.List;



@Getter
public class ProductFilter {
    private Specification<Product> spec;
    private String filterDefinition;

    public ProductFilter(MultiValueMap<String, String> params) {
        StringBuilder filterDefinitionBuilder = new StringBuilder();
        spec = Specification.where(null);


        String filterTitle = params.getFirst("title");
        if (filterTitle != null && !filterTitle.isBlank()) {
            spec = spec.and(ProductSpecifications.titleLike(filterTitle));
            filterDefinitionBuilder.append("&title=").append(filterTitle);
        }


        if (params.getFirst("min_price") != null && !params.getFirst("min_price").isBlank()) {
            Integer minPrice = Integer.parseInt(params.getFirst("min_price"));
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
            filterDefinitionBuilder.append("&min_price=").append(minPrice);
        }

        if (params.getFirst("max_price") != null && !params.getFirst("max_price").isBlank()) {
            Integer maxPrice = Integer.parseInt(params.getFirst("max_price"));
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(maxPrice));
            filterDefinitionBuilder.append("&max_price=").append(maxPrice);
        }


        if (params.containsKey("categories")){
            List<String> categories = params.get("categories");
            spec = spec.and(ProductSpecifications.haveCategory(categories));
            filterDefinitionBuilder.append("&categories=").append(categories);
        }

        filterDefinition = filterDefinitionBuilder.toString();
    }
}
