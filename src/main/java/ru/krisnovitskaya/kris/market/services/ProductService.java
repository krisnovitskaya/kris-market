package ru.krisnovitskaya.kris.market.services;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.krisnovitskaya.kris.market.entities.Product;
import ru.krisnovitskaya.kris.market.repositories.ProductRepository;
import ru.krisnovitskaya.kris.market.repositories.specifications.ProductSpecifications;

import java.util.Map;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository repository;

    public void save(Product editedProduct){
        repository.save(editedProduct);
    }

    public Product getOne(Long id){
        return repository.getOne(id);
    }

    public Page<Product> findAll(Specification<Product> spec, int page, int size) {
        return repository.findAll(spec, PageRequest.of(page, size));
    }

}
