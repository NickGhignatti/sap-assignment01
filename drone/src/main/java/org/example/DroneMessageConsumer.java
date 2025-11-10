package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DroneMessageConsumer {
    private static final Logger logger = LoggerFactory.getLogger(DroneMessageConsumer.class);
    private final DroneController controller = new DroneController();

    @RabbitListener(queues = RabbitMqConfig.DRONE_QUEUE)
    public void processOrderMessage(OrderMessage orderMessage) {
        try {
            logger.info("Received order message: {}", orderMessage);

            if (orderMessage.orderId() == null || orderMessage.orderId().isEmpty()) {
                throw new IllegalArgumentException("Order ID cannot be null or empty");
            }

            processDelivery(orderMessage);

            // logger.info("Successfully processed order: {}", orderMessage.orderId());

            Thread thread = new Thread(() -> {
                Drone drone = new Drone(orderMessage);
                drone.start();
                controller.attachDrone(drone.getId(), drone);
                Random rand = new Random();
                try {
                    Thread.sleep(rand.nextInt(orderMessage.maxDeliveryTimeMinutes()) * 1_000_000L);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                drone.end();
                controller.detachDrone(drone.getId());
            });

            thread.start();

        } catch (Exception e) {
            logger.error("Error processing order message: {}", orderMessage, e);
            throw e;
        }
    }

    private void processDelivery(OrderMessage order) {
        logger.info("Processing delivery for order: {}", order.orderId());
        logger.info("Route: {} -> {}", order.fromAddress(), order.toAddress());
        logger.info("Package details: {}kg, delivery by {}",
                order.packageWeight(), order.requestedDeliveryTime());
    }
}
