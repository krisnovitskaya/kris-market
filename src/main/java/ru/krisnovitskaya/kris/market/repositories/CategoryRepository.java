package ru.krisnovitskaya.kris.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.krisnovitskaya.kris.market.entities.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
