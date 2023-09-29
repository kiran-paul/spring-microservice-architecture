package com.kiran.paymentservice.service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiran.paymentservice.entity.Payment;
import com.kiran.paymentservice.repository.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository repository;
	
	public Payment savePayment(Payment payment) {
		payment.setPaymentStatus(paymentProcessing());
		payment.setTransactionId(UUID.randomUUID().toString());
		return repository.save(payment);
	}
	
	public String paymentProcessing() {
		//actual function should be talking to 3rd party payment gateway
		return new Random().nextBoolean()?"success":"false";
	}
	
	public List<Payment> getPayments(){
		return repository.findAll();
	}

}

