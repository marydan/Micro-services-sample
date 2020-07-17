package com.stackroute.restaurantownerservice.domain;

import java.util.Date;
import java.util.List;

public class Order {
	
	private int id;
	private String userId;
	private Date orderDate;
	private List<Item> orderItems;
	private double price;
	private String modeOfPayment;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public List<Item> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<Item> orderItems) {
		this.orderItems = orderItems;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getModeOfPayment() {
		return modeOfPayment;
	}
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	
	
	

	
}
