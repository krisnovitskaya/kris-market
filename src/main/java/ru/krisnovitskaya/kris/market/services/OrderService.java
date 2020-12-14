package ru.krisnovitskaya.kris.market.services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.krisnovitskaya.kris.market.dto.OrderDto;
import ru.krisnovitskaya.kris.market.dto.OrderItemDto;
import ru.krisnovitskaya.kris.market.entities.Order;
import ru.krisnovitskaya.kris.market.exceptions.WrongOrderStatusException;
import ru.krisnovitskaya.kris.market.repositories.OrderRepository;
import ru.krisnovitskaya.kris.market.soap.ItemOrder;
import ru.krisnovitskaya.kris.market.soap.OrderXML;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

//    public List<Order> findAll() {
//        return orderRepository.findAll();
//    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }


    public List<OrderDto> findAllUserOrdersDtosByUsername(String username) {
        return orderRepository.findAllOrdersByUsername(username).stream().map(OrderDto::new).collect(Collectors.toList());
    }

    public List<OrderDto> findAll(){
        return orderRepository.findAll().stream().map(OrderDto::new).collect(Collectors.toList());
    }

    public List<OrderDto> findAllByStatus(Order.OrderStatus status){
        return orderRepository.findOrdersByStatus(status).stream().map(OrderDto::new).collect(Collectors.toList());
    }



    public List<OrderXML> findAllUserOrderXMLByUsername(String username) {
        List<Order> orders = orderRepository.findAllOrdersByUsernameWithItems(username);

        List<OrderDto> orderDtos = orders.stream().map(OrderDto::new).collect(Collectors.toList());
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

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public Order checkAndSave(Order order, Order.OrderStatus status) {
        switch (order.getStatus()){
            case NEW:
                if(status.equals(Order.OrderStatus.DONE)){
                    order.setStatus(Order.OrderStatus.DONE);
                }else if (status.equals(Order.OrderStatus.IN_PROGRESS)){
                    order.setStatus(Order.OrderStatus.IN_PROGRESS);
                } else {
                    throw new WrongOrderStatusException("Forbidden set status " + status.name() + " for order with status " + order.getStatus().name());
                }
                break;
            case IN_PROGRESS:
                if(status.equals(Order.OrderStatus.DONE)){
                    order.setStatus(Order.OrderStatus.DONE);
                } else {
                    throw new WrongOrderStatusException("Forbidden set status " + status.name() + " for order with status " + order.getStatus().name());
                }
                break;
            case DONE:
                throw new WrongOrderStatusException("Forbidden set status " + status.name() + " for order with status " + order.getStatus().name());
            default:
                break;
        }
        return orderRepository.save(order);
    }
}
