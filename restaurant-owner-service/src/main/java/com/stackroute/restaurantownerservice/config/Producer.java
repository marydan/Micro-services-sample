package com.stackroute.restaurantownerservice.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.restaurantownerservice.dto.UserOrder;

@Component
public class Producer {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private DirectExchange exchange;
	
	public void sendMessageToRabbbitMQ(UserOrder userOrder) {
		rabbitTemplate.convertAndSend(exchange.getName(), "user-order-routing-key", userOrder);
	}

}
