package org.example;

import org.apache.commons.text.RandomStringGenerator;

import java.io.Serializable;

public class DeliveryMessage implements Serializable {
    private final String deliveryId;
    private final String startingPlace;
    private final String destinationPlace;

    public DeliveryMessage(final String startingPlace, final String destinationPlace, final Float packageWeight) {
        this.deliveryId = RandomStringGenerator.builder().get().generate(32);
        this.startingPlace = startingPlace;
        this.destinationPlace = destinationPlace;
    }

    public String getDeliveryId() {
        return this.deliveryId;
    }

    @Override
    public String toString() {
        return "DeliveryMessage [deliveryId = " + this.deliveryId + ", from " + this.startingPlace + " to " + this.destinationPlace + "]";
    }
}