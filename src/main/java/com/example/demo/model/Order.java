package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@Column(name = "id_order")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "paid")
	private boolean paid;
	
	@OneToMany(mappedBy = "order")
	private List<OrderDetail> ordersDetails;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;
	
	public Order(int id, boolean paid, List<OrderDetail> ordersDetails, User user) {
		super();
		this.id = id;
		this.paid = paid;
		this.ordersDetails = ordersDetails;
		this.user = user;
	}

	public Order() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<OrderDetail> getOrdersDetails() {
		return ordersDetails;
	}

	public void setOrdersDetails(List<OrderDetail> ordersDetails) {
		this.ordersDetails = ordersDetails;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	
}
