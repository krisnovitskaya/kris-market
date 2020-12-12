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


    public List<Category> getByListIds(List<Long> ids){
        return categoryRepository.findAllById(ids);
    }

    public Category saveOrUpdate(Category category) {
        return categoryRepository.save(category);
    }

    public Optional<Category> findByCategoryName(String name) {
        return categoryRepository.getOneByCategoryName(name);
    }
}
