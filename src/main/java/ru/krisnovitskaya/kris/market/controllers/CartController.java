package ru.krisnovitskaya.kris.market.controllers;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.krisnovitskaya.kris.market.entities.Product;
import ru.krisnovitskaya.kris.market.exceptions.ResourceNotFoundException;
import ru.krisnovitskaya.kris.market.services.ProductService;
import ru.krisnovitskaya.kris.market.utils.Cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
//@Controller
//@RequestMapping("/cart")
//@AllArgsConstructor
public class CartController {
    private ProductService productService;
    private Cart cart;

    @GetMapping
    public String showCartPage(HttpSession session) {
        return "cart";
    }

    @GetMapping("/add/{product_id}")
    public void addToCart(
            @PathVariable(name = "product_id") Long productId,
            HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        cart.addOrIncrement(productId);
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/inc/{product_id}")
    public String addOrIncrementProduct(@PathVariable(name = "product_id") Long productId) {
        cart.addOrIncrement(productId);
        return "redirect:/cart";
    }

    @GetMapping("/dec/{product_id}")
    public String decrementOrRemoveProduct(@PathVariable(name = "product_id") Long productId) {
        cart.decrementOrRemove(productId);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{product_id}")
    public String removeProduct(@PathVariable(name = "product_id") Long productId) {
        cart.remove(productId);
        return "redirect:/cart";
    }
}
