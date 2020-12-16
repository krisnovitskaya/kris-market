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
    private String username;
    private List<OrderItemDto> items;
    private int price;
    private String address;
    private long phone;
    private String status;

    public OrderDto(Order order){
        this.id = order.getId();
        this.username = order.getUser().getUsername();
        this.items = order.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.price = order.getPrice();
        this.address = order.getAddress();
        this.phone = order.getPhone();
        this.status = order.getStatus().toString();
    }
}
