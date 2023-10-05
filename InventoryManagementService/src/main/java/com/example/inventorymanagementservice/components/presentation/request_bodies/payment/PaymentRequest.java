package com.example.inventorymanagementservice.components.presentation.request_bodies.payment;

import com.example.inventorymanagementservice.components.persistence.entities.Payment;



public class PaymentRequest {

    private long orderId;

    private Payment payment;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
