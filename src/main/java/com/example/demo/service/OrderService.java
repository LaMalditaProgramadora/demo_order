package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;

public interface OrderService {
	List<Order> listOrdersByUserId(int id);
	public Order listActualOrder(int userId);
	public void createFirstOrder(Order order);
	public void addOrderDetail(OrderDetail orderDetail);
	public void payOrderActive(Order order);
}
