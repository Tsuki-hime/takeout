package cz.tsuki.takeout.repositories;

import cz.tsuki.takeout.models.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    Order findOrderByOrderID(Long orderId);

    List<Order> findAll();

}
