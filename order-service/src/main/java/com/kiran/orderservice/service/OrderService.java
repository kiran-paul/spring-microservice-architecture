package com.kiran.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kiran.orderservice.dto.Payment;
import com.kiran.orderservice.dto.TransactionRequest;
import com.kiran.orderservice.dto.TransactionResponse;
import com.kiran.orderservice.entity.Order;
import com.kiran.orderservice.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public TransactionResponse saveOrder(TransactionRequest transactionRequest) {
		
		Order order=transactionRequest.getOrder();
		Payment payment = transactionRequest.getPayment();
		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());
		
		Payment paymentResponse = restTemplate.postForObject("http://PAYMENT-SERVICE/payment/savePayment", payment, Payment.class);
		String message = paymentResponse.getPaymentStatus().equals("success")?"payment success, order placed":"payment failed";
		
		repository.save(order);
		
		return new TransactionResponse(order, order.getPrice(), paymentResponse.getTransactionId(),message);
	}

}
