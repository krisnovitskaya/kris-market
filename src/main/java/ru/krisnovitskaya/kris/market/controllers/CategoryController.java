package ru.krisnovitskaya.kris.market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.krisnovitskaya.kris.market.entities.Category;
import ru.krisnovitskaya.kris.market.entities.Product;
import ru.krisnovitskaya.kris.market.exceptions.MarketError;
import ru.krisnovitskaya.kris.market.exceptions.RegistrationError;
import ru.krisnovitskaya.kris.market.services.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping(produces = "application/json")
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }


    @Secured("ROLE_ADMIN")
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createCategory(@RequestBody Category newCategory) {
        if(newCategory.getName() == null){
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Category name is null"), HttpStatus.BAD_REQUEST);
        }
        if (categoryService.findByCategoryName(newCategory.getName()).isPresent()) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Category name " + newCategory.getName() + " is busy"), HttpStatus.BAD_REQUEST);
        }
        newCategory.setId(null);
        categoryService.saveOrUpdate(newCategory);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
