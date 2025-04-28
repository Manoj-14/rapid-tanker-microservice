package com.project.orderservice.modal;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class WaterOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userId;
    private String waterSupplierId;
    private String tankerId;
    private int quantity;
    private Status status;
    private double price;
    private Date creationDate;
    private String paymentId;
    public WaterOrder() {

    }

    public WaterOrder(int id, String userId, String waterSupplierId, String tankerId, int quantity, Status status, double price, Date creationDate, String paymentId) {
        this.id = id;
        this.userId = userId;
        this.waterSupplierId = waterSupplierId;
        this.tankerId = tankerId;
        this.quantity = quantity;
        this.status = status;
        this.price = price;
        this.creationDate = creationDate;
        this.paymentId = paymentId;
    }

    public WaterOrder(int id, String userId, String waterSupplierId, String tankerId, int quantity, Status status, double price) {
        this.id = id;
        this.userId = userId;
        this.waterSupplierId = waterSupplierId;
        this.tankerId = tankerId;
        this.quantity = quantity;
        this.status = status;
        this.price = price;
    }

    @PrePersist
    public void setDefaults(){
        this.status = Status.ORDER_PLACED;
        this.price = this.quantity * 10;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTankerId() {
        return tankerId;
    }

    public void setTankerId(String tankerId) {
        this.tankerId = tankerId;
    }

    public String getWaterSupplierId() {
        return waterSupplierId;
    }

    public void setWaterSupplierId(String waterSupplierId) {
        this.waterSupplierId = waterSupplierId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int orderId) {
        this.id = orderId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + id +
                ", userId='" + userId + '\'' +
                ", waterSupplierId='" + waterSupplierId + '\'' +
                ", tankerId='" + tankerId + '\'' +
                ", quantity=" + quantity +
                ", status=" + status +
                ", price=" + price +
                ", creationDate=" + creationDate +
                ", paymentId='" + paymentId + '\'' +
                '}';
    }
}
