package com.stackroute.restaurantownerservice.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.stackroute.restaurantownerservice.config.Producer;
import com.stackroute.restaurantownerservice.domain.Order;
import com.stackroute.restaurantownerservice.dto.UserOrder;
import com.stackroute.restaurantownerservice.exception.RestaurantNotFoundException;
import com.stackroute.restaurantownerservice.feign.client.UserOrderFiegnClient;
import com.stackroute.restaurantownerservice.service.IOrderService;
import com.stackroute.restaurantownerservice.service.IRestaurantService;

@RestController
@RequestMapping("api/v1/restaurantowner")
public class OrderController {
	
	@Autowired
	Environment environment;
	
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IRestaurantService restaurantService;
	
	@Autowired
	private UserOrderFiegnClient userOrderFiegnClient;
	
	
	  @Autowired private RestTemplate restTemplate;
	  
	  
	 
	@Autowired
	private Producer producer;
	
	private ResponseEntity<?> responseEntity;
	
	private static final String USER_SERVICE_URL = "http://user-service/api/v1/user";
	
	private static Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@GetMapping("/orders")
	public ResponseEntity<?> getAllOrders(){
		try {
			responseEntity = new ResponseEntity<>(orderService.getAllOrdersHistory(), HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error occured..", HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return responseEntity;
	}
	
	@GetMapping("/{restaurantid}/orders")
	public ResponseEntity<?> getOrdersByRestaurant(@PathVariable("restaurantid") String id) throws RestaurantNotFoundException{
		try {
			responseEntity = new ResponseEntity<>(orderService.getOrdersByRestaurant(id), HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error occured..", HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return responseEntity;
	}
	
	
	@PostMapping("/{userid}/{restaurantid}/orders")
	@HystrixCommand(fallbackMethod="userServiceDown_FallBack")
	public ResponseEntity<?> placeOrderFromRestaurant(@PathVariable("userid") String userId, @PathVariable("restaurantid") String restaurantId, @RequestBody Order order) throws RestaurantNotFoundException{
		
		String serverPort = environment.getProperty("local.server.port");
		System.out.println("Serving Order Controller at Port :::::" + serverPort);
		
		logger.info("Inside Order Controller :: Before calling User Service..");
		
		try {
		Order placedOrder = 	orderService.placeOrderFromRestaurant(userId, restaurantId, order);
		if(placedOrder == null) {
			responseEntity = new ResponseEntity<>("Some Internal error occured.. Please try again..", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else {
			//update the order details in user service
			UserOrder userOrder = new UserOrder();
			     userOrder.setUserId(userId)
			     		  .setModeOfPayment(order.getModeOfPayment())
			     		  .setOrderDate(order.getOrderDate())
			     		  .setOrderId(order.getId())
			     		  .setOrderItems(order.getOrderItems())
			     		  .setPrice(order.getPrice())
					      .setRestaurantId(restaurantId)
					      .setRestaurantName(restaurantService.getRestaurantById(restaurantId).getName());
			System.out.println("user order -->"+ userOrder);
//			HttpEntity<UserOrder> requestEntity = new HttpEntity<>(userOrder);
//			restTemplate.exchange(USER_SERVICE_URL +"/order",HttpMethod.POST,requestEntity, Boolean.class);
		//restTemplate.postForObject(USER_SERVICE_URL +"/order",userOrder,UserOrder.class);
			userOrderFiegnClient.updateUserOrder(userOrder);
			//producer.sendMessageToRabbbitMQ(userOrder);
			logger.info("Inside Order Controller :: After calling User Service..");
			
			
			responseEntity = new ResponseEntity<>(placedOrder, HttpStatus.OK);
		}
		} 
		catch(RestaurantNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>(e.getMessage()+"Some internal error occured.. Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return responseEntity;
	}
	
	private ResponseEntity<?> userServiceDown_FallBack(String userId, String restaurantId, Order order) {
		 
        System.out.println("Order Service is down!!! fallback route enabled...");
 
        return new ResponseEntity<>( "CIRCUIT BREAKER ENABLED!!! No Response From Order Service at this moment. " +
                    " Service will be back shortly - " + new Date(), HttpStatus.GATEWAY_TIMEOUT);
    }

}
