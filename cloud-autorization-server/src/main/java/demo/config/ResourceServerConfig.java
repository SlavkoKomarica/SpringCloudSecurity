package demo.config;

import java.security.Principal;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableResourceServer
@RestController
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

	@RequestMapping("/user/name")
	public String me(Principal user) {
		return user.getName();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/user/**").authorizeRequests().anyRequest()
				.authenticated();
	}
}
