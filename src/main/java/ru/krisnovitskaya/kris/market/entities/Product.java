package ru.krisnovitskaya.kris.market.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.krisnovitskaya.kris.market.dto.ProductDto;
import ru.krisnovitskaya.kris.market.dto.ProfileDto;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @Column(name = "active")
    private Boolean active;

    @ManyToMany
    @JoinTable(name = "products_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;



    public Product updateProduct(ProductDto productDto, List<Category> categories){
        this.setTitle(productDto.getTitle());
        this.setPrice(productDto.getPrice());
        this.setActive(productDto.getActive());
        this.setCategories(categories);
        return this;
    }
}
