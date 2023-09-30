package com.kiran.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Lazy;

import com.kiran.orderservice.dto.Payment;
import com.kiran.orderservice.dto.TransactionRequest;
import com.kiran.orderservice.dto.TransactionResponse;
import com.kiran.orderservice.entity.Order;
import com.kiran.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	@Lazy
	private RestTemplate restTemplate;
	
	@Value("${microservices.payment-service.endpoint}")
	private String paymentServiceEndpoint;
	
	public TransactionResponse saveOrder(TransactionRequest transactionRequest) {
		
		Order order=transactionRequest.getOrder();
		Payment payment = transactionRequest.getPayment();
		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());
		
		Payment paymentResponse = restTemplate.postForObject(paymentServiceEndpoint+"/savePayment", payment, Payment.class);
		String message = paymentResponse.getPaymentStatus().equals("success")?"payment success, order placed":"payment failed";
		
		repository.save(order);
		
		return new TransactionResponse(order, order.getPrice(), paymentResponse.getTransactionId(),message);
	}

}
