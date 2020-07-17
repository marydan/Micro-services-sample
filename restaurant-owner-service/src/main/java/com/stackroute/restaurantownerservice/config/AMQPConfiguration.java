package com.stackroute.restaurantownerservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AMQPConfiguration {
	
	private String user_order_exchange = "ORDER-EXCHANGE";
	private String user_order_queue = "USER-ORDER-QUEUE";
	private String routing_key = "user-order-routing-key";
	
	@Bean
	public DirectExchange userOrderExchange() {
		return new DirectExchange(user_order_exchange);
	}
	
	
	@Bean
	public Queue userOrderQueue() {
		return new Queue(user_order_queue,false);
	}
	
	@Bean
	public Binding binding(Queue userOrderQueue, DirectExchange userOrderExchange) {
		return BindingBuilder.bind(userOrderQueue()).to(userOrderExchange()).with(routing_key);
	}
	
	@Bean
	public Jackson2JsonMessageConverter producerJackson2JsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	
	@Bean
	  public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
	    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
	    rabbitTemplate.setMessageConverter(producerJackson2JsonMessageConverter());
	    return rabbitTemplate;
	  }
	
	
	
	
	
}
