package org.example;

public class DroneRequest {
    private String droneId;

    public DroneRequest(final String droneId) {
        this.droneId = droneId;
    }


    public String getDroneId() {
        return droneId;
    }

    public void setDroneId(final String droneId) {
        this.droneId = droneId;
    }
}