package com.stackroute.restaurantownerservice.service;

import java.util.List;

import com.stackroute.restaurantownerservice.domain.Order;
import com.stackroute.restaurantownerservice.exception.RestaurantNotFoundException;

public interface IOrderService {
	
	public List<List<Order>> getAllOrdersHistory();
	public List<Order> getOrdersByRestaurant(String id) throws RestaurantNotFoundException;
	public Order placeOrderFromRestaurant(String userId, String restaurantId, Order order) throws RestaurantNotFoundException;

}
