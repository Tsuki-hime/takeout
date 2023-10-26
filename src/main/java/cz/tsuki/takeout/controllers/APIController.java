package cz.tsuki.takeout.controllers;

import cz.tsuki.takeout.models.Order;
import cz.tsuki.takeout.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api")

public class APIController {
    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<?> saveOrder(@RequestBody Order order) {
        orderService.save(order);
        return ResponseEntity.status(201).body(order);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable Long orderId) {
        Optional<Order> maybeOrder = Optional.ofNullable(orderService.getOrderById(orderId));
        if (maybeOrder.isPresent()) {
            return ResponseEntity.status(200).body(maybeOrder.get());
        }
        return ResponseEntity.status(404).body("This id does not exist");
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getStatus(@RequestParam("status") String status) {
        return ResponseEntity.status(200).body(orderService.getAllWithStatus(status));
    }


}
