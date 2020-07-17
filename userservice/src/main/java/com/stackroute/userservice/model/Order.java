package com.stackroute.userservice.model;

import java.util.Date;
import java.util.List;

public class Order {
	
	private int orderId;
	private Date orderDate;
	private String modeOfPayment;	
	private double price;
	private List<Item> orderItems;
	private String restaurantId;
	private String restaurantName;
	private String userId;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getModeOfPayment() {
		return modeOfPayment;
	}
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double orderPrice) {
		this.price = orderPrice;
	}
	public List<Item> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<Item> orderItems) {
		this.orderItems = orderItems;
	}
	public String getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", modeOfPayment=" + modeOfPayment
				+ ", orderPrice=" + price + ", items=" + orderItems + ", restaurantId=" + restaurantId
				+ ", restaurantName=" + restaurantName + "]";
	}
	
	
	
	
	
	

}
