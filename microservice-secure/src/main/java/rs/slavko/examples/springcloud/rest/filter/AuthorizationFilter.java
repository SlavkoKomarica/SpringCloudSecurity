package rs.slavko.examples.springcloud.rest.filter;

import java.io.IOException;
import java.security.Principal;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Strings;

@Component
public class AuthorizationFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	private String authenticatedUser;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			printHeaders(httpServletRequest);
			String authorizationHeader = httpServletRequest.getHeader("authorization");
			getAuthenticatedUser(authorizationHeader);
			chain.doFilter(request, response);
		}
		
		throw new RuntimeException("Not a HttpServletRequest!. Request: "+request);		

	}
	
	private void printHeaders(HttpServletRequest req) {
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
	
	private void getAuthenticatedUser(String authorizationHeader){
		if(Strings.isNullOrEmpty(authorizationHeader)){
			throw new RuntimeException("Authorization header not present!");
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("authorization", authorizationHeader);
		
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8181/uaa/me", HttpMethod.GET, entity, String.class);
		if(!response.getStatusCode().equals(HttpStatus.OK)){
			throw new RuntimeException("Unable to get authenticated user. Response status: "+response.getStatusCode());
		}
		this.authenticatedUser = response.getBody();
		logger.info("Authenticated user: "+authenticatedUser);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	public String getAuthenticatedUser() {
		return authenticatedUser;
	}

	public void setAuthenticatedUser(String authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
	}
	
	

}
