package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> listProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product listById(int id) {
		return productRepository.findById(id).get();
	}

}
