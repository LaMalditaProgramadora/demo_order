package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Override
	public List<Order> listOrdersByUserId(int userId) {
		return orderRepository.findByUserId(userId);
	}
	
	@Override
	public Order listActualOrder(int userId) {
		return orderRepository.findByPaidAndUserId(false, userId);
	}

	@Override
	public void addOrderDetail(OrderDetail orderDetail) {
		orderDetailRepository.save(orderDetail);
	}
	
	@Override
	public void createFirstOrder(Order order) {
		orderRepository.save(order);
	}

	@Override
	public void payOrderActive(Order order) {
		order.setPaid(true);
		orderRepository.save(order);
		
		Order orderAux = new Order();
		orderAux.setPaid(false);
		orderAux.setUser(order.getUser());
		orderRepository.save(orderAux);
	}
	
}
