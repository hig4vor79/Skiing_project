package com.skiing.models.enums;

public enum OrderStatus {
    NEW("New"), CANCELED("Canceled"), ACCEPTED("Accepted"),
    DELIVERY("Delivery"), DELIVERED("Delivered"), DONE("Done");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String get() {
        return status;
    }
}
