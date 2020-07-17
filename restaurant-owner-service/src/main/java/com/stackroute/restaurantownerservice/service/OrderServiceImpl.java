package com.stackroute.restaurantownerservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.restaurantownerservice.domain.Order;
import com.stackroute.restaurantownerservice.domain.Restaurant;
import com.stackroute.restaurantownerservice.exception.RestaurantNotFoundException;
import com.stackroute.restaurantownerservice.repository.RestaurantRepository;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Override
	public List<List<Order>> getAllOrdersHistory() {
		
		try {
		return	restaurantRepository.findAll().stream().map(Restaurant::getOrdersList).collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
		
		
	}

	@Override
	public List<Order> getOrdersByRestaurant(String id) throws RestaurantNotFoundException {
		
		try {
			return	restaurantRepository.findById(id).get().getOrdersList();
			} catch (Exception e) {
				return null;
			}
	}

	@Override
	public Order placeOrderFromRestaurant(String userId, String restaurantId, Order order) throws RestaurantNotFoundException {
		
		try {
			Optional<Restaurant> restaurantExists = restaurantRepository.findById(restaurantId);
			if(restaurantExists.isPresent()) {
				Restaurant restaurant = restaurantExists.get(); 
				order.setUserId(userId);
				restaurant.getOrdersList().add(order);
				restaurantRepository.save(restaurant);				
			}
			else {
				throw new RestaurantNotFoundException();
			}
		} catch (Exception e) {
			return null;
		}
		
		return order;
	}

}
