package ru.krisnovitskaya.kris.market.services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.krisnovitskaya.kris.market.entities.Order;
import ru.krisnovitskaya.kris.market.entities.OrderItem;
import ru.krisnovitskaya.kris.market.entities.User;
import ru.krisnovitskaya.kris.market.repositories.OrderRepository;

import java.util.List;

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

    public List<Order> findByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

//    public List<Order> findByUserName(String username) {
//        return orderRepository.findAllByUserame(username);
//    }
}
