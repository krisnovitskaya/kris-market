package ru.krisnovitskaya.kris.market.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.krisnovitskaya.kris.market.dto.CartDto;
import ru.krisnovitskaya.kris.market.utils.Cart;

@RestController
@RequestMapping("api/v1/cart")
@AllArgsConstructor
public class CartController {
    private Cart cart;


    /**
     * Add selected product to cart
     * @param productId
     */
    @GetMapping("/add/{product_id}")
    public void addToCart(@PathVariable(name = "product_id") Long productId) {
        cart.addOrIncrement(productId);
    }

    /**
     * decrement quantity or remove product from cart
     * @param productId
     */
    @GetMapping("/dec/{product_id}")
    public void decrementOrRemoveProduct(@PathVariable(name = "product_id") Long productId) {
        cart.decrementOrRemove(productId);
    }

    /**
     * remove product from cart
     * @param productId
     */
    @GetMapping("/remove/{product_id}")
    public void removeProduct(@PathVariable(name = "product_id") Long productId) {
        cart.remove(productId);
    }

    /**
     * Return info about all products in the cart
     * @return CartDto
     */
    @GetMapping
    public CartDto getCartDto() {
        cart.recalculate();
        return new CartDto(cart);
    }
}

