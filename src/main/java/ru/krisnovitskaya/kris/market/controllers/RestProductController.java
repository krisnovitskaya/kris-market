package ru.krisnovitskaya.kris.market.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import ru.krisnovitskaya.kris.market.entities.Product;
import ru.krisnovitskaya.kris.market.services.ProductService;
import ru.krisnovitskaya.kris.market.utils.ProductFilter;

import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class RestProductController {
    private ProductService productService;

    @GetMapping // /api/v1/products
    public Page<Product> getAllProducts(@RequestParam(defaultValue = "1", name = "p") Integer page,
                                        @RequestParam Map<String, String> params) {
        if (page < 1) {
            page = 1;
        }
        ProductFilter productFilter = new ProductFilter(params);
        Page<Product> content = productService.findAll(productFilter.getSpec(), page - 1, 5);
        return content;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).get();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product p) {
        p.setId(null);
        return productService.saveOrUpdate(p);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product p) {
        return productService.saveOrUpdate(p);
    }

    @DeleteMapping
    public void deleteAll() {
        productService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}