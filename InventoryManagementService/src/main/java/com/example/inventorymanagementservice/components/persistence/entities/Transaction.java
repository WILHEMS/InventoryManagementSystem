package com.example.inventorymanagementservice.components.persistence.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * a bill entity.
 * @author kamar baraka.*/

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Payment payment;


    private final LocalDateTime dateTime = LocalDateTime.now();

    public long getTransactionId() {
        return transactionId;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
