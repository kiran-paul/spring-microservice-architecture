package com.kiran.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiran.orderservice.dto.Payment;
import com.kiran.orderservice.dto.TransactionRequest;
import com.kiran.orderservice.dto.TransactionResponse;
import com.kiran.orderservice.entity.Order;
import com.kiran.orderservice.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/saveOrder")
	public TransactionResponse saveOrder(@RequestBody TransactionRequest transactionRequest) {
		return orderService.saveOrder(transactionRequest);
	}
	

}
