package cz.tsuki.takeout.controllers;

import cz.tsuki.takeout.models.Order;
import cz.tsuki.takeout.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
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
        if (Objects.equals(status, "done") || Objects.equals(status, "inprogress") || Objects.equals(status, "ordered")) {
            return ResponseEntity.status(200).body(orderService.getAllWithStatus(status));
        }
        return ResponseEntity.status(422).body("Invalid status.");
    }

    @PatchMapping("/orders/{orderId}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long orderId, @RequestParam String newStatus) {
        Optional<Order> maybeOrder = Optional.ofNullable(orderService.getOrderById(orderId));
        if (maybeOrder.isEmpty()) {
            return ResponseEntity.status(404).body("No order with this id found.");
        } else if (Objects.equals(newStatus, "done") || Objects.equals(newStatus, "inprogress") || Objects.equals(newStatus, "ordered")) {
            orderService.updateStatus(newStatus, orderId);
            return ResponseEntity.status(202).body("Status updated.");
        } else {
            return ResponseEntity.status(422).body("Invalid status.");
        }
    }


}
