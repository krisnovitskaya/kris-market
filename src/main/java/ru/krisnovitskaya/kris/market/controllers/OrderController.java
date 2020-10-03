package ru.krisnovitskaya.kris.market.controllers;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.krisnovitskaya.kris.market.entities.Order;
import ru.krisnovitskaya.kris.market.services.OrderService;
import ru.krisnovitskaya.kris.market.utils.Cart;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    private Cart cart;


    @GetMapping
    public String firstRequest(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "orders";
    }

    @GetMapping("/new")
    public String newOrder(Model model){
        return "new_order";
    }

    @PostMapping("/make_order")
    public String makeOrder(@RequestParam(name = "customer_name") String customerName, @RequestParam(name = "customer_phone") int customerPhone,
                           @RequestParam(name = "customer_address") String customerAddress){
        Order order = new Order();
        order.setCustomerAddress(customerAddress);
        order.setCustomerName(customerName);
        order.setCustomerPhone(customerPhone);
        order.setPrice(cart.getPrice());
        order.setItems(cart.getItems());
        orderService.saveOrUpdate(order);
        cart.getItems().clear();
        return "redirect:/orders";
    }


}
