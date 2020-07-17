package com.stackroute.userservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.userservice.controller.UserController;
import com.stackroute.userservice.model.Order;
import com.stackroute.userservice.service.IUserService;

@Component
public class Consumer {
	
	private static final Logger LOG = LoggerFactory.getLogger(Consumer.class);


//	@Autowired
//	private IUserService userService;
//	@RabbitListener(queues = "USER-ORDER-QUEUE")
//	public void getUserOrderFromRabbitMQ(Order order) {
//		LOG.info("User Order is received .." + order);
//		userService.saveUserOrder(order);
	//}
//	
}
