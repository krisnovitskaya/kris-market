package ru.krisnovitskaya.kris.market.controllers;



import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.krisnovitskaya.kris.market.dto.CartDto;
import ru.krisnovitskaya.kris.market.dto.OrderDto;
import ru.krisnovitskaya.kris.market.entities.Order;
import ru.krisnovitskaya.kris.market.entities.User;
import ru.krisnovitskaya.kris.market.services.OrderService;
import ru.krisnovitskaya.kris.market.services.UserService;
import ru.krisnovitskaya.kris.market.utils.Cart;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {
    private UserService userService;
    private OrderService orderService;
    private Cart cart;

    @GetMapping(produces = "application/json")
    public List<OrderDto> showOrders(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<Order> orders = orderService.findByUser(user);
        List<OrderDto> orderDtos  = orders.stream().map(OrderDto::new).collect(Collectors.toList());
        return orderDtos;
    }


    @PostMapping("/create")
    public void makeOrder(Principal principal,
                          @RequestParam(name = "phone") int phone,
                            @RequestParam(name = "address") String address){
        User user = userService.findByUsername(principal.getName());
        Order order = new Order(user, cart, address, phone);
        orderService.save(order);
    }
}

