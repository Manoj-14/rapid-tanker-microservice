package com.project.paymentservice.service;

import com.project.paymentservice.modal.Payment;
import com.project.paymentservice.modal.Status;
import jakarta.transaction.Transactional;

import java.util.UUID;

public interface PaymentService {
    @Transactional
    Payment createPayment(Payment payment);
    Payment updatePayment(Status status);
    Payment getPaymentById(UUID id);
}
