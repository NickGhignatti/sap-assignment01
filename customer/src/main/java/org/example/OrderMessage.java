package org.example;

import org.apache.commons.text.RandomStringGenerator;

import java.io.Serializable;

public class OrderMessage implements Serializable {
    private final String packageId;
    private final Float packageWeight;

    public OrderMessage(final Float packageWeight) {
        this.packageId = RandomStringGenerator.builder().get().generate(32);
        this.packageWeight = packageWeight;
    }

    public String getPackageId() {
        return this.packageId;
    }

    @Override
    public String toString() {
        return "PackageMessage [packageId = " + this.packageId + ", weight = " + this.packageWeight + "]";
    }
}
