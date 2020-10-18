package ru.krisnovitskaya.kris.market.utils;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.krisnovitskaya.kris.market.entities.OrderItem;
import ru.krisnovitskaya.kris.market.entities.Product;
import ru.krisnovitskaya.kris.market.exceptions.ResourceNotFoundException;
import ru.krisnovitskaya.kris.market.services.ProductService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class Cart {
    private final ProductService productService;
    private List<OrderItem> items;
    private int price;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void addOrIncrement(Long productId) {
        for (OrderItem o : items) {
            if (o.getProduct().getId().equals(productId)) {
                o.incrementQuantity();
                recalculate();
                return;
            }
        }
        Product p = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + productId + " (add to cart)"));
        items.add(new OrderItem(p));
        recalculate();
    }

    public void decrementOrRemove(Long productId) {
        Iterator<OrderItem> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItem o = iter.next();
            if (o.getProduct().getId().equals(productId)) {
                o.decrementQuantity();
                if (o.getQuantity() == 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void remove(Long productId) {
        Iterator<OrderItem> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItem o = iter.next();
            if (o.getProduct().getId().equals(productId)) {
                iter.remove();
                recalculate();
                return;
            }
        }
    }

    public void recalculate() {
        price = 0;
        for (OrderItem o : items) {
            o.setPricePerProduct(o.getProduct().getPrice());
            o.setPrice(o.getProduct().getPrice() * o.getQuantity());
            price += o.getPrice();
        }
    }

    public void clear() {
        items.clear();
        price = 0;
    }
}