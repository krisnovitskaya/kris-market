package ru.krisnovitskaya.kris.market.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.krisnovitskaya.kris.market.entities.Order;
import ru.krisnovitskaya.kris.market.entities.User;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);
}
