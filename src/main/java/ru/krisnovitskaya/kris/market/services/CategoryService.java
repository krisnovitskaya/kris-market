package ru.krisnovitskaya.kris.market.services;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.krisnovitskaya.kris.market.entities.Category;
import ru.krisnovitskaya.kris.market.entities.Product;
import ru.krisnovitskaya.kris.market.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getOneById(Long id){
        return categoryRepository.getOne(id);
    }

    public List<Category> getByListIds(List<Long> ids){
        return categoryRepository.findAllById(ids);
    }
}
