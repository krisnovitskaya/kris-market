package ru.krisnovitskaya.kris.market.services;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.krisnovitskaya.kris.market.entities.Product;
import ru.krisnovitskaya.kris.market.repositories.ProductRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository repository;

    public List<Product> findAll(){
        return repository.findAll();
    }

    public List<Product> findAllByPriceLessThanEqual(int max){
        return  repository.findAllByPriceLessThanEqual(max);
    }

    public List<Product> findAllByPriceGreaterThanEqual(int min){
        return  repository.findAllByPriceGreaterThanEqual(min);
    }

    public List<Product> findAllByPriceLessThanEqualAndGreaterThanEqual(int min, int max){
        return repository.findAllByPriceLessThanEqualAndGreaterThanEqual(min, max);
    }


//
    public List<Product> findAllLimit(int page){
        int limit = 5;
        return repository.findAllLimit(limit, page*limit);
    }

    public List<Product> findLessThanMaxPrice(int maxPrice, int page){
        int limit = 5;
        return repository.findLessThanMaxPrice(maxPrice, limit, page*limit);
    }

    public List<Product> findGreaterThanMinPrice(int minPrice, int page){
        int limit = 5;
        return repository.findGreaterThanMinPrice(minPrice, limit, page*limit);
    }


    public List<Product> findGreaterThanMinPriceAndLessThanMaxPrice(int minPrice, int maxPrice, int page){
        int limit = 5;
        return repository.findGreaterThanMinPriceAndLessThanMaxPrice(minPrice, maxPrice, limit, page*limit);
    }

}
