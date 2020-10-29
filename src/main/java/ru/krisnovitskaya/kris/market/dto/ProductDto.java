package ru.krisnovitskaya.kris.market.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.krisnovitskaya.kris.market.entities.Product;



@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private int price;
    //private List<String> categories;

    public ProductDto(Product p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.price = p.getPrice();
        //this.categories = p.getCategories().;
    }
}
