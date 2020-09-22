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



    @GetMapping("/get")
    public String showLimit(Model model, @RequestParam(name = "max", defaultValue = "-1") int maxPrice, @RequestParam(name = "min", defaultValue = "-1") int minPrice, @RequestParam(name = "page", defaultValue = "1") Integer page) {
        if (page < 1) {
            page = 1;
        }
        if (minPrice == -1 && maxPrice == -1) {
            model.addAttribute("products", service.findAllLimit(page - 1));
        } else if (maxPrice == -1) {
            model.addAttribute("products", service.findGreaterThanMinPrice(minPrice, page - 1));
        } else if (minPrice == -1) {
            model.addAttribute("products", service.findLessThanMaxPrice(maxPrice, page - 1));
        } else {
            model.addAttribute("products", service.findGreaterThanMinPriceAndLessThanMaxPrice(minPrice, maxPrice, page - 1));
        }
        return "products";
    }


    @GetMapping
    public String showAllProducts(Model model, @RequestParam(name = "max", defaultValue = "-1") int maxPrice, @RequestParam(name = "min", defaultValue = "-1") int minPrice) {
        if (minPrice == -1 && maxPrice == -1) {
            model.addAttribute("products", service.findAll());
        } else if (maxPrice == -1) {
            model.addAttribute("products", service.findAllByPriceGreaterThanEqual(minPrice));
        } else if (minPrice == -1) {
            model.addAttribute("products", service.findAllByPriceLessThanEqual(maxPrice));
        } else {
            model.addAttribute("products", service.findAllByPriceLessThanEqualAndGreaterThanEqual(minPrice, maxPrice));
        }
        return "products";
    }


}

