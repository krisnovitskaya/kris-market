package ru.krisnovitskaya.kris.market.controllers;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.krisnovitskaya.kris.market.entities.Product;
import ru.krisnovitskaya.kris.market.services.ProductService;

import java.util.List;

@Controller
@RequestMapping("/prod")
@AllArgsConstructor
public class ProductController {
    private ProductService service;


    @GetMapping
    public String showAllProducts(Model model, @RequestParam(name = "max", defaultValue = "-1") int maxPrice, @RequestParam(name = "min", defaultValue = "-1") int minPrice) {
        if(minPrice == -1 && maxPrice == -1){
            model.addAttribute("products", service.findAll());
        } else if(maxPrice == -1){
            model.addAttribute("products", service.findByPriceGreaterThanEqual(minPrice));
        } else if(minPrice == -1){
            model.addAttribute("products", service.findByPriceLessThanEqual(maxPrice));
        }else{
            model.addAttribute("products", service.findAllByPriceLessThanEqualAndGreaterThanEqual(minPrice, maxPrice));
        }

        return "products";
    }



}

