package rs.slavko.examples.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableEurekaClient
@EnableOAuth2Sso
public class MicroserviceSelfSecured extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceSelfSecured.class, args);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**")
				.authorizeRequests()
				.antMatchers("/greeting").permitAll()
				.antMatchers("/greeting-secure").authenticated()
				.antMatchers("/greeting-secure-user").hasRole("USER")
				.antMatchers("/greeting-secure-admin").hasRole("ADMIN")
				.anyRequest().authenticated().and().csrf().disable();
	}
}
