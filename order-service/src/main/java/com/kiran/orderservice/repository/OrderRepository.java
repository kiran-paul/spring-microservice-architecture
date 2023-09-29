package com.kiran.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiran.orderservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>{

}
