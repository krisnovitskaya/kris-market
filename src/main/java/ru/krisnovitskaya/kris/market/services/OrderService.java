package ru.krisnovitskaya.kris.market.services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.krisnovitskaya.kris.market.dto.OrderDto;
import ru.krisnovitskaya.kris.market.dto.OrderItemDto;
import ru.krisnovitskaya.kris.market.entities.Order;
import ru.krisnovitskaya.kris.market.entities.OrderItem;
import ru.krisnovitskaya.kris.market.entities.User;
import ru.krisnovitskaya.kris.market.repositories.OrderRepository;
import ru.krisnovitskaya.kris.market.soap.ItemOrder;
import ru.krisnovitskaya.kris.market.soap.OrderXML;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }


    public List<OrderDto> findAllUserOrdersDtosByUsername(String username) {
        return orderRepository.findAllOrdersByUsername(username).stream().map(OrderDto::new).collect(Collectors.toList());
    }

    public List<OrderXML> findAllUserOrderXMLByUsername(String username) {
        List<OrderDto> orderDtos = this.findAllUserOrdersDtosByUsername(username);
        List<OrderXML> ordersXML = new ArrayList<>();
        for (OrderDto orderDto : orderDtos) {
            OrderXML orderXML = new OrderXML();
            orderXML.setId(orderDto.getId());
            orderXML.setUsername(orderDto.getUsername());
            orderXML.setAddress(orderDto.getAddress());
            orderXML.setPhone(orderDto.getPhone());
            orderXML.setPrice(orderDto.getPrice());
            List<OrderItemDto> orderItemDtos = orderDto.getItems();
            for (OrderItemDto orderItemDto : orderItemDtos) {
                ItemOrder itemOrder = new ItemOrder();
                itemOrder.setProductId(orderItemDto.getProductId());
                itemOrder.setProductTitle(orderItemDto.getProductTitle());
                itemOrder.setPrice(orderItemDto.getPrice());
                itemOrder.setPricePerProduct(orderItemDto.getPricePerProduct());
                itemOrder.setQuantity(orderItemDto.getQuantity());
                orderXML.getItemOrder().add(itemOrder);
            }
            ordersXML.add(orderXML);
        }
        return ordersXML;
    }
}
