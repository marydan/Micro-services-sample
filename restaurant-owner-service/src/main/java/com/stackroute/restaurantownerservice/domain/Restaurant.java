package com.stackroute.restaurantownerservice.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Restaurant {

	@Id
	private String id;
	private String name;
	private String email;
	private String contactNo;
	private Address address;
	private List<Order> ordersList;	
	private String restaurantOwner;
	
	
	
	public String getRestaurantOwner() {
		return restaurantOwner;
	}
	public void setRestaurantOwner(String restaurantOwner) {
		this.restaurantOwner = restaurantOwner;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Order> getOrdersList() {
		return ordersList;
	}
	public void setOrdersList(List<Order> ordersList) {
		this.ordersList = ordersList;
	}
	
	
}
