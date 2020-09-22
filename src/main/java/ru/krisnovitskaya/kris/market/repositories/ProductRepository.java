package ru.krisnovitskaya.kris.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.krisnovitskaya.kris.market.entities.Product;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.price <= ?1")
    List<Product> findAllByPriceLessThanEqual(int max);

    @Query("select p from Product p where p.price >= ?1")
    List<Product> findAllByPriceGreaterThanEqual(int min);

    @Query("select p from Product p where p.price >= ?1 and p.price <= ?2")
    List<Product> findAllByPriceLessThanEqualAndGreaterThanEqual(int min, int max);

}
