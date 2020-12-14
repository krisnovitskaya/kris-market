package ru.krisnovitskaya.kris.market.controllers;


import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.krisnovitskaya.kris.market.dto.PageDto;
import ru.krisnovitskaya.kris.market.dto.ProductDto;
import ru.krisnovitskaya.kris.market.entities.Category;
import ru.krisnovitskaya.kris.market.entities.Product;
import ru.krisnovitskaya.kris.market.exceptions.MarketError;
import ru.krisnovitskaya.kris.market.exceptions.ResourceNotFoundException;
import ru.krisnovitskaya.kris.market.repositories.specifications.ProductSpecifications;
import ru.krisnovitskaya.kris.market.services.CategoryService;
import ru.krisnovitskaya.kris.market.services.ProductService;
import ru.krisnovitskaya.kris.market.utils.ProductFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping(produces = "application/json") // /api/v1/products
    public PageDto<ProductDto> getAllProducts(@RequestParam(defaultValue = "1", name = "p") Integer page,
                                              @RequestParam MultiValueMap<String, String> params) {
        if (page < 1) {
            page = 1;
        }

        ProductFilter productFilter = new ProductFilter(params);
        PageDto<ProductDto> ppdto = productService.findAll(productFilter.getSpec().and(ProductSpecifications.isActive()), page - 1, 5);
        return  ppdto;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + id));
    }

    @Secured("ROLE_ADMIN")
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createProduct(@RequestBody Product p, @RequestParam MultiValueMap<String, String> params) {
        if(p.getTitle() == null){
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Product title = null"),HttpStatus.BAD_REQUEST);
        }
        if(p.getPrice() < 0){
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Product price is negative"),HttpStatus.BAD_REQUEST);
        }
        List<Category> productCategories = new ArrayList<>();
        if(params.containsKey("categories")){
            List<Long> ids = params.get("categories").stream().map(s -> Long.parseLong(s)).collect(Collectors.toList());
            productCategories = categoryService.getByListIds(ids);
        }
        p.setId(null);
        p.setCategories(productCategories);
        productService.saveNewProduct(p);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping(consumes = "application/json", produces = "application/json")
    public Product updateProduct(@RequestBody Product p) {
        return productService.saveOrUpdate(p);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping
    public void deleteAll() {
        productService.deleteAll();
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}