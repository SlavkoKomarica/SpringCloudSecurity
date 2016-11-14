package com.example.filter;

/**
 * Created by slavko.komarica on 11/7/2016.
 */

import java.util.Enumeration;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class SimpleFilter extends ZuulFilter {

	private static Logger logger = LoggerFactory.getLogger(SimpleFilter.class);

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		logger.info(String.format("%s request to %s", request.getMethod(), request
				.getRequestURL().toString()));
		printHeaders(request);

		return null;
	}

	public void printHeaders(HttpServletRequest req) {
		Enumeration<String> headerNames = req.getHeaderNames();

		while (headerNames.hasMoreElements()) {

			String headerName = headerNames.nextElement();
			logger.info(headerName);

			Enumeration<String> headers = req.getHeaders(headerName);
			while (headers.hasMoreElements()) {
				String headerValue = headers.nextElement();
				logger.info(headerName + ": " + headerValue);
			}

		}
	}

}
