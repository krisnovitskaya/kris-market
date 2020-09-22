package ru.krisnovitskaya.kris.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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




    @Query(value =
            "select * from products p where p.price <= :max limit :limit offset :offset", nativeQuery = true)
    List<Product> findLessThanMaxPrice(
            @Param("max") int max, @Param("limit") int limit,
            @Param("offset") int offset);

    @Query(value =
            "select * from products p where p.price >= :min limit :limit offset :offset", nativeQuery = true)
    List<Product> findGreaterThanMinPrice(
            @Param("min") int min, @Param("limit") int limit,
            @Param("offset") int offset);

    @Query(value =
            "select * from products p where p.price >= :min and p.price <= :max limit :limit offset :offset", nativeQuery = true)
    List<Product> findGreaterThanMinPriceAndLessThanMaxPrice(
            @Param("min") int min, @Param("max") int max,@Param("limit") int limit,
            @Param("offset") int offset);

    @Query(value =
            "select * from products p limit :limit offset :offset", nativeQuery = true)
    List<Product> findAllLimit(
            @Param("limit") int limit,
            @Param("offset") int offset);

}
