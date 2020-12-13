package ru.krisnovitskaya.kris.market.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.krisnovitskaya.kris.market.entities.Order;
import ru.krisnovitskaya.kris.market.entities.User;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.user.username = ?1")
    List<Order> findAllOrdersByUsername(String username);

    @Query("select distinct o from Order o join fetch o.items where o.user.username = ?1")
    List<Order> findAllOrdersByUsernameWithItems(String username);

    @Query("select o from Order o where o.status = ?1")
    List<Order> findOrdersByStatus(Order.OrderStatus status);

}
