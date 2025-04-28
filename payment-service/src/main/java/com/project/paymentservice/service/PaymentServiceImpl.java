package com.project.paymentservice.service;

import com.project.paymentservice.modal.Payment;
import com.project.paymentservice.modal.Status;
import com.project.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment updatePayment(Status status) {
        return null;
    }

    @Override
    public Payment getPaymentById(UUID id) {
        return null;
    }
}
