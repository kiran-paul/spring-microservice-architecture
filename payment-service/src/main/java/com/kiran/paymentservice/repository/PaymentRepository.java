package com.kiran.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiran.paymentservice.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
