package org.example;

import java.util.UUID;

enum DroneState {
    Sleeping,
    InTransit,
    Returning
}
public class Drone {
    private final String droneId;
    private final OrderMessage order;
    private DroneState state = DroneState.Sleeping;

    public Drone(final OrderMessage order) {
       this.droneId = UUID.randomUUID().toString();
       this.order = order;
    }

    public String getId() {
        return this.droneId;
    }

    public void start() {
        this.state = DroneState.InTransit;
        System.out.println("Drone " + this.droneId + " delivering to " + this.order.toAddress());
    }

    public void end() {
        this.state = DroneState.Returning;
        System.out.println("Drone " + this.droneId + " ended the delivery");
    }
}