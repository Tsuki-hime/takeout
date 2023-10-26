package cz.tsuki.takeout.services;

import cz.tsuki.takeout.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class OrderService {
    private final OrderRepository orderRepository;

}
