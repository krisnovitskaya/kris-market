package ru.krisnovitskaya.kris.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.krisnovitskaya.kris.market.entities.Order;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {

    private Long id;
    //private User user;
    private List<OrderItemDto> items;
    private int price;
    private String address;
    private int phone;

    public OrderDto(Order order){
        this.id = order.getId();
        this.items = order.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.price = order.getPrice();
        this.address = order.getAddress();
        this.phone = order.getPhone();
    }
}
