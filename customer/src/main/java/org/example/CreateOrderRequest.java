package org.example;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class CreateOrderRequest {
    private String customerId;
    private String fromAddress;
    private String toAddress;
    private double packageWeight;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime requestedDeliveryTime;

    private int maxDeliveryTimeMinutes;

    // Constructors
    public CreateOrderRequest() {}

    public CreateOrderRequest(String customerId, String fromAddress, String toAddress,
                              double packageWeight, LocalDateTime requestedDeliveryTime,
                              int maxDeliveryTimeMinutes) {
        this.customerId = customerId;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.packageWeight = packageWeight;
        this.requestedDeliveryTime = requestedDeliveryTime;
        this.maxDeliveryTimeMinutes = maxDeliveryTimeMinutes;
    }

    // Getters and Setters
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getFromAddress() { return fromAddress; }
    public void setFromAddress(String fromAddress) { this.fromAddress = fromAddress; }

    public String getToAddress() { return toAddress; }
    public void setToAddress(String toAddress) { this.toAddress = toAddress; }

    public double getPackageWeight() { return packageWeight; }
    public void setPackageWeight(double packageWeight) { this.packageWeight = packageWeight; }

    public LocalDateTime getRequestedDeliveryTime() { return requestedDeliveryTime; }
    public void setRequestedDeliveryTime(LocalDateTime requestedDeliveryTime) {
        this.requestedDeliveryTime = requestedDeliveryTime;
    }

    public int getMaxDeliveryTimeMinutes() { return maxDeliveryTimeMinutes; }
    public void setMaxDeliveryTimeMinutes(int maxDeliveryTimeMinutes) {
        this.maxDeliveryTimeMinutes = maxDeliveryTimeMinutes;
    }
}