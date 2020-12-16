package ru.krisnovitskaya.kris.market.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.krisnovitskaya.kris.market.dto.CartDto;
import ru.krisnovitskaya.kris.market.dto.OrderDto;
import ru.krisnovitskaya.kris.market.entities.Order;
import ru.krisnovitskaya.kris.market.entities.User;
import ru.krisnovitskaya.kris.market.exceptions.MarketError;
import ru.krisnovitskaya.kris.market.exceptions.ResourceNotFoundException;
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


    /**
     * Return info about orders current authenticated user
     * @param principal
     * @return List<OrderDto>
     */
    @GetMapping(produces = "application/json")
    public List<OrderDto> showOrders(Principal principal) {
        return orderService.findAllUserOrdersDtosByUsername(principal.getName());
    }


    /**
     * Create new currentusers` order and save it, data from cart, clear cart after save
     * @param principal
     * @param phone must be positive and 10 length
     * @param address must be not null
     * @return HttpStatus Created or Bad Request
     */
    @PostMapping("/create")
    public ResponseEntity<?> makeOrder(Principal principal,
                          @RequestParam(name = "phone") long phone,
                          @RequestParam(name = "address") String address) {


        if((phone < 0) || (String.valueOf(phone).length() != 10)){
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Wrong input Phone number. Phone length must be 10"), HttpStatus.BAD_REQUEST);
        }

        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to create order for user: " + principal.getName() + ". User doesn't exist"));
        Order order = new Order(user, cart, address, phone);
        orderService.save(order);
        cart.clear();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    /**
     * Return ALL users Orders with status, only for ADMIN and MANAGER
     * @param status
     * @return List<OrderDto>
     */
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    @PostMapping(value = "/get", produces = "application/json")
    public List<OrderDto> getAllOrdersByStatus(Order.OrderStatus status) {
        if (status == null) {
            return orderService.findAll();
        } else {
            return orderService.findAllByStatus(status);
        }
    }


    /**
     * Update order status by input order.id and new status only for ADMIN and MANAGER
     * @param id
     * @param status
     * @return HttpStatus
     */
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    @PutMapping("/set_status")
    public ResponseEntity<?> updateStatus(@RequestParam Long id, Order.OrderStatus status) {
        if(status == null){
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Wrong status"), HttpStatus.BAD_REQUEST);
        }
        Order order = orderService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find Order with id: " + id));
        orderService.checkAndSave(order, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

