package cz.tsuki.takeout.services;

import cz.tsuki.takeout.models.Order;
import cz.tsuki.takeout.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor

public class OrderService {
    private final OrderRepository orderRepository;


    public void save(Order order) {
        orderRepository.save(order);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findOrderByOrderID(orderId);
    }

    public List<Order> getAllWithStatus(String status) {
        List<Order> orders = orderRepository.findAll();
        List<Order> withStatus = new ArrayList<>();
        for (Order o : orders) {
            if (Objects.equals(o.getStatus(), status)) {
                withStatus.add(o);
            }
        }
        return withStatus;
    }

    public void updateStatus(String newStatus, Long orderId){
        Order order = orderRepository.findOrderByOrderID(orderId);
        order.setStatus(newStatus);
        orderRepository.save(order);
    }

}
