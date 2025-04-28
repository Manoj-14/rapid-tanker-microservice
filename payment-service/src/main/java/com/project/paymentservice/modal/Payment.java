package com.project.paymentservice.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String from;
    private String to;
    private BigDecimal amount;
    private Status status;
    private String orderId;

    public Payment() {
    }

    public Payment(UUID id, String from, String to, BigDecimal amount, Status status, String orderId) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.status = status;
        this.orderId = orderId;
    }

    public void setDefaults(){
        this.status = Status.PAYMENT_PENDING ;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", amount=" + amount +
                ", status=" + status +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
