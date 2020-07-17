package com.stackroute.restaurantownerservice.dto;

import java.util.Date;
import java.util.List;

import com.stackroute.restaurantownerservice.domain.Item;

public class UserOrder {

	private int orderId;
	private Date orderDate;
	private String modeOfPayment;	
	private double price;
	private List<Item> orderItems;
	private String restaurantId;
	private String restaurantName;
	private String userId;
	
	
	public int getOrderId() {
		return orderId;
	}
	public UserOrder setOrderId(int orderId) {
		this.orderId = orderId;
		return this;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public UserOrder setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
		return this;
	}
	public String getModeOfPayment() {
		return modeOfPayment;
		
	}
	public UserOrder setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
		return this;
	}
	
	public double getPrice() {
		return price;
	}
	public UserOrder setPrice(double orderPrice) {
		this.price = orderPrice;
		return this;
	}
	public List<Item> getOrderItems() {
		return orderItems;
	}
	public UserOrder setOrderItems(List<Item> orderItems) {
		this.orderItems = orderItems;
		return this;
	}
	public String getRestaurantId() {
		return restaurantId;
	}
	public UserOrder setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
		return this;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public UserOrder setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
		return this;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public UserOrder setUserId(String userId) {
		this.userId = userId;
		return this;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", modeOfPayment=" + modeOfPayment
				+ ", orderPrice=" + price + ", items=" + orderItems + ", restaurantId=" + restaurantId
				+ ", restaurantName=" + restaurantName + "]";
	}
	
	
	
}
