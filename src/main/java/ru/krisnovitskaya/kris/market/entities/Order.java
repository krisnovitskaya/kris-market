package ru.krisnovitskaya.kris.market.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import ru.krisnovitskaya.kris.market.utils.Cart;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderItem> items;

    @Column(name = "price")
    private int price;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private int phone;

    public Order(User user, Cart cart, String address, int phone) {
        this.user = user;
        this.price = cart.getPrice();
        this.items = new ArrayList<>();
        this.address = address;
        this.phone = phone;
        cart.getItems().forEach(oi -> {
            oi.setOrder(this);
            items.add(oi);
        });
        cart.clear();
    }

}