package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.service.ProductService;

@Controller
@RequestMapping("/product")
@SessionAttributes("userSession")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/")
	public String goListProducts(Map<String, Object> model) {
		model.put("listProducts", productService.listProducts());
		return "listProducts";
	}
	
	@RequestMapping("/listProducts")
	public String listProducts(Map<String, Object> model) {
		model.put("listProducts", productService.listProducts());
		return "listProducts";
	}
}
