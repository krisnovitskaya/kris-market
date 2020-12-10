package ru.krisnovitskaya.kris.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.krisnovitskaya.kris.market.utils.Cart;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CartDto {
    private List<OrderItemDto> items;
    private Integer price;

    public CartDto(Cart cart) {
        this.price = cart.getPrice();
        this.items = cart.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
    }
}
