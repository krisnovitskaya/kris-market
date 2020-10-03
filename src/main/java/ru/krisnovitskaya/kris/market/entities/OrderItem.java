package ru.krisnovitskaya.kris.market.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_per_product")
    private int pricePerProduct;

    @Column(name = "price")
    private int price;

    public OrderItem(Product p) {
        this.product = p;
        this.quantity = 1;
        this.price = p.getPrice();
        this.pricePerProduct = p.getPrice();
    }

    public void incrementQuantity() {
        quantity++;
        price = pricePerProduct * quantity;
    }

    public void decrementQuantity() {
        quantity--;
        price = pricePerProduct * quantity;
    }
}
