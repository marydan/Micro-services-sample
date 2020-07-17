package com.stackroute.zuulservice.filter;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import com.sun.scenario.effect.FilterContext;

public class PostFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		// TODO Auto-generated method stub
		System.out.println("Inside Post Filter...");
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "post-filter";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return FilterConstants.SEND_RESPONSE_FILTER_ORDER;
	}

}
