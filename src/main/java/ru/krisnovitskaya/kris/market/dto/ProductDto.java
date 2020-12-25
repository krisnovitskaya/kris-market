package ru.krisnovitskaya.kris.market.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.krisnovitskaya.kris.market.entities.Category;
import ru.krisnovitskaya.kris.market.entities.Product;

import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private int price;
    private List<String> categories;
    private Boolean active;

    public ProductDto(Product p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.price = p.getPrice();
        this.categories = p.getCategories().stream().map(Category::getName).collect(Collectors.toList());
        this.active = p.getActive();
    }
}
