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


    /**
     * Return info about active products
     * @param page number
     * @param params for create specification
     * @return PageDto<ProductDto>
     */
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


    /**
     * Return all info about Product with input id
     * @param id
     * @return Product or MarketError if Product with id not Found
     */
    @GetMapping(value = "/{id}", produces = "application/json")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + id));
    }


    /**
     * only for user with ROLE_ADMIN
     * @param p new Product
     * @param params includes data about new product categories
     * @return HttpStatus
     */
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
            List<Long> ids = params.get("categories").stream().map(Long::parseLong).collect(Collectors.toList());
            productCategories = categoryService.getByListIds(ids);
        }
        p.setId(null);
        p.setCategories(productCategories);
        productService.saveNewProduct(p);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * only for user with ROLE_ADMIN
     * @param p update Product
     * @param params includes data about new product categories, or null if categories do not need change
     * @return HttpStatus
     */
    @Secured("ROLE_ADMIN")
    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateProduct(@RequestBody Product p, @RequestParam MultiValueMap<String, String> params) {
        if(p.getTitle() == null){
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Product new Title = null"),HttpStatus.BAD_REQUEST);
        }
        if(p.getPrice() < 0){
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Product new Price is negative"),HttpStatus.BAD_REQUEST);
        }
        List<Category> productCategories = new ArrayList<>();
        if(params.containsKey("categories")){
            List<Long> ids = params.get("categories").stream().map(Long::parseLong).collect(Collectors.toList());
            productCategories = categoryService.getByListIds(ids);
            p.setCategories(productCategories);
        }
        ProductDto dto = new ProductDto(productService.saveOrUpdate(p));
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

}