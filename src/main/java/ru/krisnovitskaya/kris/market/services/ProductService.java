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

    public List<Product> findByPriceLessThanEqual(int max){
        return  repository.findAllByPriceLessThanEqual(max);
    }

    public List<Product> findByPriceGreaterThanEqual(int min){
        return  repository.findAllByPriceGreaterThanEqual(min);
    }

    public List<Product> findAllByPriceLessThanEqualAndGreaterThanEqual(int min, int max){
        return repository.findAllByPriceLessThanEqualAndGreaterThanEqual(min, max);
    }

}
