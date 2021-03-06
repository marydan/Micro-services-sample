package com.stackroute.zuulservice.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class PreFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		HttpServletRequest httpRequest = RequestContext.getCurrentContext().getRequest();
		System.out.println("Request Method:" + httpRequest.getMethod());
		System.out.println("Requested URL :" + httpRequest.getRequestURL().toString());
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre-filter";
	}

	@Override
	public int filterOrder() {
		return FilterConstants.PRE_DECORATION_FILTER_ORDER;
	}

}
