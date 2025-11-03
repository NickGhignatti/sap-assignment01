package org.example;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final RabbitTemplate rabbitTemplate;

    public OrderController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    public OrderResponse createOrder(@RequestBody OrderRequest request) {
        String orderId = UUID.randomUUID().toString();

        OrderMessage message = new OrderMessage(
                request.getOrderWeight()
        );

        // Send message to RabbitMQ
        rabbitTemplate.convertAndSend(
                RabbitMqConfig.ORDER_EXCHANGE,
                RabbitMqConfig.ORDER_ROUTING_KEY,
                message
        );

        System.out.println("Order created and sent to queue: " + message);

        return new OrderResponse(orderId, "Order received and being processed");
    }

    @GetMapping("/health")
    public String health() {
        return "Customer service is running!";
    }
}

class OrderRequest {
    private Float orderWeight;

    public Float getOrderWeight() { return orderWeight; }
    public void setOrderWeight(Float orderWeight) {
        this.orderWeight = orderWeight;
    }
}

class OrderResponse {
    private String orderId;
    private String message;

    public OrderResponse(String orderId, String message) {
        this.orderId = orderId;
        this.message = message;
    }

    // Getters and Setters
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}