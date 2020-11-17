package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;

@Controller
@RequestMapping("/order")
@SessionAttributes("userSession")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/")
	public String GolistActualOrder(Map<String, Object> model, @ModelAttribute("userSession") User userSession) {
		model.put("listOrders", orderService.listOrdersByUserId(userSession.getId()));
		return "listOrders";
	}
	
	@RequestMapping("/listOrders")
	public String listOrders(Map<String, Object> model, @ModelAttribute("userSession") User userSession) {
		model.put("listOrders", orderService.listOrdersByUserId(userSession.getId()));
		return "listOrders";
	}
	
	@RequestMapping("/listActualOrder") // Cart
	public String listActualOrder(Map<String, Object> model, @ModelAttribute("userSession") User userSession) {
		Order orderAux = orderService.listActualOrder(userSession.getId());
		model.put("listOrdersDetail", orderAux.getOrdersDetails());
		return "listOrdersDetail";
	}
	
	@RequestMapping("/addToActualOrder") // Cart
	public String addToActualOrder(Map<String, Object> model, @ModelAttribute("userSession") User userSession, @RequestParam(value = "productId") Integer productId) {
		Order orderAux = orderService.listActualOrder(userSession.getId());
		OrderDetail orderDetailAux = new OrderDetail();
		Product productAux = productService.listById(productId);
		
		orderDetailAux.setProduct(productAux);
		orderDetailAux.setQuantity(1); // Para cambiar esto, agregar otro @RequestParam con la cantidad
		orderDetailAux.setOrder(orderAux);
		
		orderService.addOrderDetail(orderDetailAux);
		
		model.put("listOrdersDetail", orderAux.getOrdersDetails());
		return "listOrdersDetail";
	}
	
	@RequestMapping("/pay") // Cart
	public String pay(Map<String, Object> model, @ModelAttribute("userSession") User userSession) {
		Order orderAux = orderService.listActualOrder(userSession.getId());
		orderService.payOrderActive(orderAux);		
		System.out.print("Llegó aquí");
		orderAux = orderService.listActualOrder(userSession.getId());
		model.put("listOrdersDetail", orderAux.getOrdersDetails());
		return "listOrdersDetail";
	}
}
