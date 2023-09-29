package com.kiran.paymentservice.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiran.paymentservice.entity.Payment;
import com.kiran.paymentservice.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/savePayment")
	public Payment savePayment(@RequestBody Payment payment) {
		return paymentService.savePayment(payment);
	}
	
	@GetMapping("/getPayments")
	public List<Payment> getPayments() {
		return paymentService.getPayments();
	}

	

}
