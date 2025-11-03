package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderMessage> createOrder(@RequestBody CreateOrderRequest request) {
        logger.info("Received order creation request: from {} to {}",
                request.getFromAddress(), request.getToAddress());

        // Use current time if not specified
        LocalDateTime deliveryTime = request.getRequestedDeliveryTime() != null
                ? request.getRequestedDeliveryTime()
                : LocalDateTime.now().plusHours(2);

        OrderMessage order = orderService.createOrder(
                request.getCustomerId(),
                request.getFromAddress(),
                request.getToAddress(),
                request.getPackageWeight(),
                deliveryTime,
                request.getMaxDeliveryTimeMinutes()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Customer Service is running");
    }
}
